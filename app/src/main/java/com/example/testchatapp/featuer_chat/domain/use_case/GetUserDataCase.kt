package com.example.testchatapp.featuer_chat.domain.use_case

import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository

class GetUserDataCase {

  val repo : ChatRepository = RepositoryImpl()
    fun getUserData(){
        repo.getUserData()
    }
}