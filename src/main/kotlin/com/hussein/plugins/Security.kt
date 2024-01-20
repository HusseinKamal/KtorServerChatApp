package com.hussein.plugins

import com.hussein.session.ChatSession
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import io.ktor.util.generateNonce

fun Application.configureSecurity() {

    install(Sessions) {
        cookie<ChatSession>("MY_SESSION")
    }
    intercept(ApplicationCallPipeline.Features){
        if(call.sessions.get<ChatSession>() == null){
            val username = call.parameters["username"] ?: "Guest"
            call.sessions.set(ChatSession(username, generateNonce()))
        }
    }
}
