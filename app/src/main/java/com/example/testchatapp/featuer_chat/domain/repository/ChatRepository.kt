package com.example.testchatapp.featuer_chat.domain.repository

import com.example.testchatapp.feature_authetication.domain.model.Users

interface ChatRepository {

    suspend fun  getAllUsers(users:ArrayList<Users>):ArrayList<Users>
}