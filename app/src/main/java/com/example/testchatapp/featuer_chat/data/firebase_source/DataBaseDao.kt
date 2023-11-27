package com.example.testchatapp.featuer_chat.data.firebase_source

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener

interface DataBaseDao {

    suspend fun getAllUsers(): Iterable<DataSnapshot>

}