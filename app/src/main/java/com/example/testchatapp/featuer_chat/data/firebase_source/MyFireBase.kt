package com.example.testchatapp.featuer_chat.data.firebase_source

import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await


class MyFireBase : DataBaseDao {
    private lateinit var database: DatabaseReference
    override suspend fun getAllUsers(users: ArrayList<Users>): ArrayList<Users> {

        database = Firebase.database.reference
        database.child("Users").get().await()
        runBlocking(Dispatchers.IO) {
            launch(Dispatchers.IO) {
            }.join()
        }
        println("====================> done list of users  ")
        return users
    }

    override suspend fun getUsers(users: ArrayList<Users>): ArrayList<Users> {
        return getAllUsers(users)
    }



}