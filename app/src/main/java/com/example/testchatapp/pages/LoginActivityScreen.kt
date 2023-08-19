package com.example.testchatapp.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivityLoginScreenBinding
class LoginActivityScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        WindowCompat.setDecorFitsSystemWindows(window, false)

    }
}