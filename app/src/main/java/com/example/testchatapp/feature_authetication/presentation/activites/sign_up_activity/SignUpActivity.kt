package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.testchatapp.databinding.ActivitySignUpBinding
import com.example.testchatapp.databinding.ActivitySignUpBinding.inflate

class SignUpActivity : AppCompatActivity() {
    private  lateinit var binding: ActivitySignUpBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        WindowCompat.setDecorFitsSystemWindows(window, false)

    }
}