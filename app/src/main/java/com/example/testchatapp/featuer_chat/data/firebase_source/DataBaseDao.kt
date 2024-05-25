package com.example.testchatapp.featuer_chat.data.firebase_source

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

interface DataBaseDao {

    suspend fun getAllUsers(listener: ValueEventListener)
    suspend fun saveFriendUser(userId:String,friendUser: UsersUnfriend)
    suspend fun getUserFriendsList():Iterable<DataSnapshot>
    suspend fun sendMessage(roomId: String, msgId: String, msg: Message)
    suspend fun getMessages(roomId : String, listener: ValueEventListener)
}