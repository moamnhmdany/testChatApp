package com.example.testchatapp.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.databinding.ActivityUsersChatListBinding.inflate

class UsersChatListActivity : AppCompatActivity() {
  private  lateinit var binding: ActivityUsersChatListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)

    }
}