package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class ChatMessangerPage : AppCompatActivity() {
    private lateinit var binding: ActivityChatMessangerPageBinding
    private val action = ChatMessangerlistenre()
    private val setting = Utiles()

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = setting.settingChatMessangerPage(this, this)
        action.goToMain(this, ui)
        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
               intent.getParcelableExtra("userDataFriend", UsersUnfriend::class.java)
        } else {
            intent.getParcelableExtra<UsersUnfriend>("userDataFriend")
        }

            println("-----------------"+userData!!.userUnfriendUserName)

    }

}
