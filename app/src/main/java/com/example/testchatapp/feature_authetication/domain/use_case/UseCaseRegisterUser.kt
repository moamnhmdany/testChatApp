package com.example.testchatapp.feature_authetication.domain.use_case

import android.content.Context
import com.example.testchatapp.feature_authetication.data.repository.ChatRepositoryImpl
import com.example.testchatapp.feature_authetication.domain.Repository.Auth_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users

class UseCaseRegisterUser(context: Context) {

     private val chatRepo : Auth_Repository = ChatRepositoryImpl(context)

  suspend  fun  createUser(user:Users ,context: Context){
        chatRepo.createNewUserWithEmail(user , context)
    }

}