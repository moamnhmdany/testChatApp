package com.example.testchatapp.feature_authetication.domain.use_case

import com.example.testchatapp.feature_authetication.data.repository.Chat_Repository_Impl
import com.example.testchatapp.feature_authetication.domain.Repository.Chat_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users

class UseCase_RegisterUser {

     lateinit var user: Users
    val repo = Chat_Repository_Impl()

    fun createUser(user:Users){
        repo.createNewUserWithEmail(user)
    }

}