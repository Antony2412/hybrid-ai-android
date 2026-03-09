package com.antony.hybridai.decision
import com.antony.hybridai.intent.IntentResult

class DecisionEngine {

    fun decide(
        intent: IntentResult,
        isOnline: Boolean
    ): ExecutionMode {

        return when {

            !isOnline ->
                ExecutionMode.ON_DEVICE

            intent.isPrivate ->
                ExecutionMode.ON_DEVICE

            intent.complexity > 0.7 ->
                ExecutionMode.CLOUD

            else ->
                ExecutionMode.ON_DEVICE
        }
    }
}