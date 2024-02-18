package com.example.testchatapp.featuer_chat.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot

interface ChatRepository {

    suspend fun  getAllUsers():Iterable<DataSnapshot>
    suspend fun getUserFriendsList():Iterable<DataSnapshot>
    suspend fun saveFriendUser(userId:String,FriendUser: UsersUnfriend)
}