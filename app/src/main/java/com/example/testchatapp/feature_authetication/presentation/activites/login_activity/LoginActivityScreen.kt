package com.example.testchatapp.feature_authetication.presentation.activites.login_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.testchatapp.databinding.ActivityLoginScreenBinding
import com.example.testchatapp.databinding.ActivityLoginScreenBinding.inflate
import com.example.testchatapp.feature_authetication.domain.model.Users

class LoginActivityScreen : AppCompatActivity() {
    private  lateinit var binding: ActivityLoginScreenBinding
    val user1 = Users()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        extracted()
        WindowCompat.setDecorFitsSystemWindows(window, false)

    }

    private fun extracted() {
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}