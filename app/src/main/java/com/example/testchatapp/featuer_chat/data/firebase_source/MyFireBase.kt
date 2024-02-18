package com.example.testchatapp.featuer_chat.data.firebase_source

import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class MyFireBase : DataBaseDao {
    private lateinit var database: DatabaseReference
    private val usersRef = Firebase.database.reference.child("Users")
    private var usersFriendListRef = Firebase.database.reference.child("UserFriends")

    override suspend fun getAllUsers():Iterable<DataSnapshot> {
       val snapshot = usersRef.get().await().children
        println("====================> done get snap shoot from firebase list of users  ")
        return snapshot
    }


    override   suspend fun saveFriendUser(userId:String,FriendUser:UsersUnfriend){
        usersRef.child(FirebaseAuth.getInstance().uid.toString()).
          child("UserFriends").child(FriendUser.userUnfriendId).setValue(FriendUser).await()
        }


    override suspend fun getUserFriendsList():Iterable<DataSnapshot>{
        val snapshot = usersRef.child(FirebaseAuth.getInstance().uid.toString())
            .child("UserFriends").get().await().children
        return snapshot
    }

}

