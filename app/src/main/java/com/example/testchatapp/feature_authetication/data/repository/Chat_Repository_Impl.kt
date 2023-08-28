package com.example.testchatapp.feature_authetication.data.repository

import android.content.Context
import com.example.testchatapp.feature_authetication.data.firebase_source.MyDataBaseDao
import com.example.testchatapp.feature_authetication.data.firebase_source.FireeBase
import com.example.testchatapp.feature_authetication.domain.Repository.Chat_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Chat_Repository_Impl() :Chat_Repository{

      val myDataBaseDao : MyDataBaseDao = FireeBase()
    override suspend fun createNewUserWithEmail(user: Users,context: Context) {

             myDataBaseDao.createUserWithEmailAndPassword(user,context)


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