package com.example.testchatapp.featuer_chat.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

interface ChatRepository {

    suspend fun  getAllUsers(listener: ValueEventListener)
    suspend fun getUserFriendsList(listener: ValueEventListener)
    suspend fun saveFriendUser(userId:String,FriendUser: UsersUnfriend)
    suspend fun sendMessage(roomId: String, msgId: String, msg: Message)
    suspend fun getMessages(roomId : String, listener: ValueEventListener)
}