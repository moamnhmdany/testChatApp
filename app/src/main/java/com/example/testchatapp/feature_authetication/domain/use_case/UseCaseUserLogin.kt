package com.example.testchatapp.feature_authetication.domain.use_case

import android.content.Context
import com.example.testchatapp.feature_authetication.data.repository.ChatRepositoryImpl
import com.example.testchatapp.feature_authetication.domain.Repository.Auth_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class UseCaseUserLogin {



    private val chatRepo : Auth_Repository = ChatRepositoryImpl()

    suspend fun login(user: Users, context: Context): Task<AuthResult> {
       return chatRepo.loginWithEmail(user,context)
    }
}