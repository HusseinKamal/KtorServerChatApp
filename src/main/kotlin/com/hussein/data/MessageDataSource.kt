package com.hussein.data

import com.hussein.data.model.Message

interface MessageDataSource {
    suspend fun getAllMessages():List<Message>

    suspend fun insertMessage(message: Message)
}