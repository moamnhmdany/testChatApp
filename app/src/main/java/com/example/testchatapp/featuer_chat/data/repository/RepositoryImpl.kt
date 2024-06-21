package com.example.testchatapp.featuer_chat.data.repository

import com.example.testchatapp.featuer_chat.data.firebase_source.DataBaseDao
import com.example.testchatapp.featuer_chat.data.firebase_source.MyFireBase
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.google.firebase.database.ValueEventListener

class RepositoryImpl() : ChatRepository {
      val data :DataBaseDao = MyFireBase()

    override suspend fun getAllUsers(listener: ValueEventListener){
      return  data.getAllUsers(listener)
    }

    override suspend fun saveFriendUser(userId: String, FriendUser: UsersUnfriend) {
        data.saveFriendUser(userId,FriendUser)
    }

    override suspend fun sendMessage(roomId: String, msgId: String, msg: Message) {
        data.sendMessage(roomId,msgId,msg)
    }

    override suspend fun getUserFriendsList(listener: ValueEventListener){

       data.getUserFriendsList(listener)

   }
    override suspend fun getMessages(roomId : String, listener: ValueEventListener){
         data.getMessages(roomId, listener)
    }
  override  fun getUserData(){
      data.getUserData()
  }
}
