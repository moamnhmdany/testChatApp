package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.feature_authetication.presentation.util.Utiles
import com.google.android.material.timepicker.TimeFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatMessangerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatMessangerPageBinding
    private val action = ChatMessangerlistenre()
    private val setting = Utiles()

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = setting.settingChatMessangerPage(this, this)
        action.goToMain(this, ui)
        action.sendMessage(ui,intent)
       val time = DateTimeFormatter.ofPattern(
            "hh:mm:ss a", Locale("en")
        ).format(LocalTime.now())
        println("--------------------the time is =${time}")

        action.observeMessageList(intent,this,ui,this)
    }

}
