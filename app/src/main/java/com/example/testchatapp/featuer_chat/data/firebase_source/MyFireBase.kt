package com.example.testchatapp.featuer_chat.data.firebase_source

import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class MyFireBase : DataBaseDao {
    private val usersRef = Firebase.database.reference.child("Users")
    private val chatsRef = Firebase.database.reference.child("chats")

    private var usersFriendListRef = Firebase.database.reference.child("UserFriends")

    override suspend fun getAllUsers(): Iterable<DataSnapshot> {
        val snapshot = usersRef.get().await().children
        println("====================> done get snap shoot from firebase list of users  ")
        return snapshot
    }


    override suspend fun saveFriendUser(userId: String, friendUser: UsersUnfriend) {
        usersRef.child(FirebaseAuth.getInstance().uid.toString()).child("UserFriends")
            .child(friendUser.userUnfriendId).setValue(friendUser).await()

         usersRef.child(FirebaseAuth.getInstance().uid.toString()).get().addOnSuccessListener {
            val data = it.getValue(Users::class.java)
             val id = friendUser.userUnfriendId
             friendUser.userId= friendUser.userUnfriendId
             friendUser.userUnfriendUserName = data!!.userName
             friendUser.userUnfriendId = data.id

             usersRef.child(id).child("UserFriends")
                 .child(FirebaseAuth.getInstance().uid.toString()).setValue(friendUser)
        }
    }

    override suspend fun getUserFriendsList(): Iterable<DataSnapshot> {
        val snapshot = usersRef.child(FirebaseAuth.getInstance().uid.toString())
            .child("UserFriends").get().await().children
        return snapshot
    }


    override suspend fun sendMessage(roomId: String, msgId: String, msg:Message){
        chatsRef.child(roomId).child(msgId).setValue(msg).await()
    }
    override suspend fun getMessages(roomId : String, listener: ValueEventListener) {

        chatsRef.child(roomId).addValueEventListener(listener)
       println("-----------------> getMessages fun in firebase  is done running")

    }






}

