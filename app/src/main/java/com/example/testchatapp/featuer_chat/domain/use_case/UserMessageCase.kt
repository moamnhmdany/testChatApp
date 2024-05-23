package com.example.testchatapp.featuer_chat.domain.use_case

import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.google.firebase.database.ValueEventListener

class UserMessageCase {
    val repository: ChatRepository = RepositoryImpl()



    suspend fun getMessages(roomId: String, listener: ValueEventListener){

        repository.getMessages(roomId,listener)

    }


}