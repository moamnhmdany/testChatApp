package com.example.testchatapp.featuer_chat.presentation.adapters

import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend


interface ChatFriend {
    fun openChat(usersUnfriendData: UsersUnfriend)
}