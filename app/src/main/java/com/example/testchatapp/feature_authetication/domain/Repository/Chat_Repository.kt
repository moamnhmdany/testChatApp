package com.example.testchatapp.feature_authetication.domain.Repository

import com.example.testchatapp.feature_authetication.domain.model.Users

interface Chat_Repository {

    fun createNewUserWithEmail( user: Users)
    fun loginWithGoogle(user:Users)
    fun loginWithTwitter(user:Users)
    fun lginWithFacebook(user: Users)
    fun loginWithEmail(user: Users)
    fun getUsers():ArrayList<Users>
}