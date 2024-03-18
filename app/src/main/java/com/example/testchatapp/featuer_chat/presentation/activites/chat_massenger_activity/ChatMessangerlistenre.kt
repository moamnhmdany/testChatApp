package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.content.Intent
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity.UsersChatListActivity

class ChatMessangerlistenre {

    fun goToMain(context: Context,ui:ActivityChatMessangerPageBinding){
        ui.btnBackChatMsg.setOnClickListener {
        val intent = Intent(context,UsersChatListActivity::class.java)
        context.startActivity(intent)
      }
    }



}