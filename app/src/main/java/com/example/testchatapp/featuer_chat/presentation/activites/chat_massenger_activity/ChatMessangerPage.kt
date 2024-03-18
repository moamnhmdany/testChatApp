package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class ChatMessangerPage : AppCompatActivity() {
    private lateinit var binding: ActivityChatMessangerPageBinding
    val action = ChatMessangerlistenre()
    val setting = Utiles()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = setting.settingChatMessangerPage(this, this)
        action.goToMain(this, ui)
    }
}
