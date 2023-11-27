package com.example.testchatapp.featuer_chat.data.firebase_source

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class MyFireBase : DataBaseDao {
    private lateinit var database: DatabaseReference
    private val usersRef = Firebase.database.reference.child("Users")
    override suspend fun getAllUsers():Iterable<DataSnapshot> {
       val snapshot = usersRef.get().await().children
        println("====================> done list of users  ")
        return snapshot
    }






}