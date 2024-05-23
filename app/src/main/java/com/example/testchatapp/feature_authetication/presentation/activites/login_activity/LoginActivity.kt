package com.example.testchatapp.feature_authetication.presentation.activites.login_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class LoginActivity : AppCompatActivity() {
    private val utilti2 = Utiles()
    private val viewModel = ViewModelLogin(this)
    private val action = LoginActionsListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = utilti2.settingLogin(this,this@LoginActivity)
         action.checkUser(this)
         action.login(ui,this,viewModel)
         action.openSignUp(this,ui)

    }


}