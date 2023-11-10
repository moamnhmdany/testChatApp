package com.example.testchatapp.featuer_chat.data.firebase_source

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class MyFireBase : DataBaseDao {
    private lateinit var database: DatabaseReference
    override suspend fun getAllUsers() {

        database = Firebase.database.reference
        database.child("UserUnfriend").get().await()

        println("====================> done list of users  ")

    }

    override suspend fun getUsers() {
         getAllUsers()
    }

    override suspend fun moveUserToFriendList() {

        database = Firebase.database.reference


        println("====================> done move  user to list of user friends  ")

    }


}