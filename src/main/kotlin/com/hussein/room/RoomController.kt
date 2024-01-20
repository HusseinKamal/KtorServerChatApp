package com.hussein.room

import com.hussein.data.MessageDataSource
import com.hussein.data.model.Message
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(private val messageDataSource: MessageDataSource) {

    private val members = ConcurrentHashMap<String,Member>() //key = username & value = Member object

    fun onJoin(
        username :String,
        sessionId :String,
        socket : WebSocketSession
        ){
        if(members.containsKey(username)){
            throw MemberAlreadyExistsException()
        }

        members[username] = Member(
            username, sessionId, socket
        )
    }

    suspend fun sendMessage(senderUsername :String, message :String){
        members.values.forEach { member->
            val messageEntity = Message(
                text = message,
                username = senderUsername,
                timeStamp = System.currentTimeMillis()
            )
            messageDataSource.insertMessage(messageEntity)

            val parsedMessage = Json.encodeToString(messageEntity)
            member.socket.send(Frame.Text(parsedMessage))
        }
    }

    suspend fun getAllMessages():List<Message>{
        return messageDataSource.getAllMessages()
    }

    suspend fun disconnect(username: String){
        members[username]?.socket?.close()
        if(members.containsKey(username)){
            members.remove(username)
        }

    }
}