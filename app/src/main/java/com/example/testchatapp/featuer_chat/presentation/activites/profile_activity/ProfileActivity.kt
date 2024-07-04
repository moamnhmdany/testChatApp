package com.example.testchatapp.featuer_chat.presentation.activites.profile_activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testchatapp.R
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class ProfileActivity : AppCompatActivity() {
    private val setting = Utiles()
    val action = ProfileActivityListenre()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = setting.settingProfileEdite(this, this)
        action.setupImage(ui, this)
        action.backToMain(this, ui)
        action.saveInfo(this, ui)
    }

}