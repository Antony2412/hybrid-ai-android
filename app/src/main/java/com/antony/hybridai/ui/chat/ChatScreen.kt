package com.antony.hybridai.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.RichText

@Composable
fun ChatScreen(viewModel: ChatViewModel = viewModel()) {

    val messages by viewModel.messages.collectAsState()
    val isTyping by viewModel.isTyping.collectAsState()

    var input by remember { mutableStateOf("") }

    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .imePadding()
    ) {

        // TOP BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Hybrid AI Assistant",
                style = MaterialTheme.typography.titleMedium
            )

            TextButton(
                onClick = { viewModel.clearMessages() }
            ) {
                Text("Clear")
            }
        }

        Divider()

        // CHAT AREA
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(12.dp),
            reverseLayout = false
        ) {

            items(messages) { message ->
                ChatBubble(message)
            }

            if (isTyping) {
                item {
                    TypingBubble()
                }
            }
        }

        Divider()

        // INPUT BAR
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("Ask something...") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (input.isNotBlank()) {
                        viewModel.sendMessage(input)
                        input = ""
                    }
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Send")
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {

    val isUser = message.isUser

    val backgroundColor =
        if (isUser) Color(0xFF6C5CE7)
        else Color.White

    val textColor =
        if (isUser) Color.White
        else Color.Black

    val alignment =
        if (isUser) Arrangement.End
        else Arrangement.Start

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = alignment
    ) {

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(backgroundColor),
            modifier = Modifier.widthIn(max = 320.dp)
        ) {

            Column(
                modifier = Modifier.padding(14.dp)
            ) {

                if (isUser) {

                    Text(
                        text = message.text,
                        color = textColor,
                        fontSize = 16.sp
                    )

                } else {

                    RichText {
                        Markdown(content = message.text)
                    }
                }
            }
        }
    }
}

@Composable
fun TypingBubble() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start
    ) {

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(Color.LightGray),
            modifier = Modifier.widthIn(max = 200.dp)
        ) {

            Text(
                text = "AI is typing...",
                modifier = Modifier.padding(14.dp),
                fontSize = 14.sp
            )
        }
    }
}