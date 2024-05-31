package com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity

import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom

interface UserRoomIntent {

    fun goChat(user : UserChatRoom)
}