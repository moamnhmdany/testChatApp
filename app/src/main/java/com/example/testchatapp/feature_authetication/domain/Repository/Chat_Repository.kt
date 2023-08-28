package com.example.testchatapp.feature_authetication.domain.Repository

import android.content.Context
import com.example.testchatapp.feature_authetication.domain.model.Users

interface Chat_Repository {

    suspend fun createNewUserWithEmail(user: Users, context: Context)
    fun loginWithGoogle(user:Users)
    fun loginWithTwitter(user:Users)
    fun lginWithFacebook(user: Users)
    fun loginWithEmail(user: Users)
    fun getUsers():ArrayList<Users>
}