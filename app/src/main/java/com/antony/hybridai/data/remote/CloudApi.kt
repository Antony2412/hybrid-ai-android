package com.antony.hybridai.data.remote

import com.antony.hybridai.data.remote.dto.ChatRequest
import com.antony.hybridai.data.remote.dto.ChatResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CloudApi {

    @POST("/chat")
    suspend fun chat(
        @Body request: ChatRequest
    ): ChatResponse
}