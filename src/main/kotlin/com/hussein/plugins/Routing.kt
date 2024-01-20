package com.hussein.plugins

import com.hussein.room.RoomController
import com.hussein.routes.chatSocket
import com.hussein.routes.getAllMessages
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent.inject
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val roomController by inject<RoomController>()
    install(Routing){
        chatSocket(roomController)
        getAllMessages(roomController)
    }
}
