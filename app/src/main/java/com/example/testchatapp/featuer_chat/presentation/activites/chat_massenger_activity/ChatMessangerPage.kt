package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding.inflate

class ChatMessangerPage : AppCompatActivity() {
    private  lateinit var binding: ActivityChatMessangerPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
