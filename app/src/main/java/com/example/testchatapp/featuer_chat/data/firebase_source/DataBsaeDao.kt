package com.example.testchatapp.featuer_chat.data.firebase_source

import com.example.testchatapp.feature_authetication.domain.model.Users

interface DataBsaeDao {

    fun getAllUsers(users: Users)

}