package com.example.testchatapp.featuer_chat.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.data.firebase_source.DataBaseDao
import com.example.testchatapp.featuer_chat.data.firebase_source.MyFireBase
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot

class RepositoryImpl : ChatRepository {
      val data :DataBaseDao = MyFireBase()

    override suspend fun getAllUsers() :Iterable<DataSnapshot>{
      return  data.getAllUsers()
    }

    override suspend fun saveFriendUser(userId: String, FriendUser: UsersUnfriend) {
        data.saveFriendUser(userId,FriendUser)
    }
   override suspend fun getUserFriendsList():Iterable<DataSnapshot>{
      return data.getUserFriendsList()
   }

}
