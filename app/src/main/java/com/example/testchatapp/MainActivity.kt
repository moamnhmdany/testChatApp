package com.example.testchatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.testchatapp.databinding.ActivityMainBinding
import com.example.testchatapp.databinding.ActivityUsersChatListBinding.inflate


class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth ;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = MainActivityBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)
    }
}