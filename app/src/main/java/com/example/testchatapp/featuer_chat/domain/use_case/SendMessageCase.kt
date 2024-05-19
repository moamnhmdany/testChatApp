package com.example.testchatapp.featuer_chat.domain.use_case

import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.models.Message

class SendMessageCase {

    private val repo = RepositoryImpl()


    suspend fun send(roomId: String, msgId: String, msg:Message){
        repo.sendMessage(roomId,msgId,msg)
    }

}
