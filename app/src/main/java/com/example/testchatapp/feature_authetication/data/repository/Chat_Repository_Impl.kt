package com.example.testchatapp.feature_authetication.data.repository

import com.example.testchatapp.feature_authetication.domain.Repository.Chat_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users

class Chat_Repository_Impl() :Chat_Repository{
    override fun createNewUserWithEmail(user: Users) {
        TODO("Not yet implemented")
    }

    override fun loginWithGoogle(user: Users) {
        TODO("Not yet implemented")
    }

    override fun loginWithTwitter(user: Users) {
        TODO("Not yet implemented")
    }

    override fun lginWithFacebook(user: Users) {
        TODO("Not yet implemented")
    }

    override fun loginWithEmail(user: Users) {
        TODO("Not yet implemented")
    }

    override fun getUsers(): ArrayList<Users> {
        TODO("Not yet implemented")
    }

}