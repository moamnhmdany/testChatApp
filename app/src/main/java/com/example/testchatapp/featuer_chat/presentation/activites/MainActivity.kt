package com.example.testchatapp.featuer_chat.presentation.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.R
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)
    }
}