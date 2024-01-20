package com.hussein

import com.hussein.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

//https://piehost.com/websocket-tester
fun Application.module(mainModule: Any) {
    install(Koin){
        module(com.hussein.di.mainModule)
    }
    configureSockets()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureRouting()
}
