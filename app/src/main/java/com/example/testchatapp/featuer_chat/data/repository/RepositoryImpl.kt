package com.example.testchatapp.featuer_chat.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.data.firebase_source.DataBaseDao
import com.example.testchatapp.featuer_chat.data.firebase_source.MyFireBase
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.example.testchatapp.feature_authetication.domain.model.Users

class RepositoryImpl : ChatRepository {
      val data :DataBaseDao = MyFireBase()

    override suspend fun getAllUsers() {
        data.getUsers()
    }

}
