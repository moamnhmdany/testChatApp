package com.example.testchatapp.featuer_chat.domain.use_case

import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

class UsersFriendCase {
       val repo : ChatRepository = RepositoryImpl()


         suspend fun saveFriend(userId:String, FriendUser:UsersUnfriend){
            repo.saveFriendUser(userId, FriendUser )
        }

    suspend fun getUserFriendsList(listener: ValueEventListener){
         repo.getUserFriendsList(listener)
    }
}