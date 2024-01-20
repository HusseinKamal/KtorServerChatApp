package com.hussein

import com.hussein.di.mainModule
import com.hussein.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

//https://piehost.com/websocket-tester
// Testing socket used : ws://localhost:8080/chat-socket -->not wss://localhost:8080/chat-socket(This for https)
@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    install(Koin) {
        modules(mainModule)
    }
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
