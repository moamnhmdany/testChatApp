package com.example.testchatapp.featuer_chat.data.firebase_source

import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.ValueEventListener

interface DataBaseDao {

    suspend fun getAllUsers(users: ArrayList<Users>): ArrayList<Users>
    suspend fun getUsers(users: ArrayList<Users>): ArrayList<Users>
}