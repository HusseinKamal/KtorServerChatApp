package com.hussein.room

import io.ktor.websocket.WebSocketSession

data class Member(
    val username:String,
    val sessionId:String,
    val socket : WebSocketSession
)
