package com.example.testchatapp.feature_authetication.data.repository

import com.example.testchatapp.feature_authetication.data.firebase_source.FireBaseDao
import com.example.testchatapp.feature_authetication.data.firebase_source.FireeBase
import com.example.testchatapp.feature_authetication.domain.Repository.Chat_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users

class Chat_Repository_Impl() :Chat_Repository{
    val fireBase = FireeBase()
    override fun createNewUserWithEmail(user: Users) {

        fireBase.createUserWithEmailAndPassword(user)
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