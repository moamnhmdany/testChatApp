package com.example.testchatapp.feature_authetication.domain.use_case

import android.content.Context
import com.example.testchatapp.feature_authetication.data.repository.Chat_Repository_Impl
import com.example.testchatapp.feature_authetication.domain.Repository.Chat_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users

class UseCase_RegisterUser() {

     lateinit var user: Users
     lateinit var repo :Chat_Repository
     val chatRepo : Chat_Repository = Chat_Repository_Impl()

  suspend  fun  createUser(user:Users ,context: Context){
        chatRepo.createNewUserWithEmail(user , context)
    }

}