package com.antony.hybridai.ui.chat


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antony.hybridai.data.remote.RetrofitClient
import com.antony.hybridai.data.remote.dto.ChatRequest
import com.antony.hybridai.decision.DecisionEngine
import com.antony.hybridai.intent.IntentClassifier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import android.util.Log
import com.antony.hybridai.decision.ExecutionMode
import com.antony.hybridai.local_ai.TinyLlamaModel

class ChatViewModel : ViewModel() {

    private val classifier = IntentClassifier()
    private val decisionEngine = DecisionEngine()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    private val _isTyping = MutableStateFlow(false)
    val isTyping: StateFlow<Boolean> = _isTyping

    private var localModel: TinyLlamaModel? = null

    fun initLocalModel(context: Context) {
        localModel = TinyLlamaModel(context)
    }
    fun sendMessage(text: String) {

        Log.d("HybridAI", "User message sent: $text")

        val userMessage = ChatMessage(text, true)
        _messages.update { it + userMessage }

        val intent = classifier.classify(text)

        Log.d(
            "HybridAI",
            "Intent classified. Complexity: ${intent.complexity}, Private: ${intent.isPrivate}"
        )

        val execution = decisionEngine.decide(intent, true)

        Log.d("HybridAI", "Execution mode decided: $execution")

        viewModelScope.launch {

            try {

                _isTyping.value = true   // START typing indicator

                if (execution == ExecutionMode.CLOUD) {

                    Log.d("HybridAI", "Sending request to cloud AI")

                    val response =
                        RetrofitClient.api.chat(ChatRequest(text))

                    Log.d("HybridAI", "Cloud response received")

                    _messages.update {
                        it + ChatMessage(response.reply, false)
                    }

                } else {

                    Log.d("HybridAI", "Handled locally")

                    val localResponse =
                        localModel?.generate(text)
                            ?: "Local model not ready"

                    _messages.update {
                        it + ChatMessage(localResponse, false)
                    }
                }

            } catch (e: Exception) {

                Log.e("HybridAI", "Network error: ${e.message}")

                _messages.update {
                    it + ChatMessage(
                        "⚠ Network error: ${e.message}",
                        false
                    )
                }

            } finally {

                _isTyping.value = false   // STOP typing indicator
            }
        }
    }

    fun clearMessages() {
        _messages.value = emptyList()
    }
}