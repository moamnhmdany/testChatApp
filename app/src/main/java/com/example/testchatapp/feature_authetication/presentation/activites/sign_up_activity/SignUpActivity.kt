package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class SignUpActivity : AppCompatActivity() {
    val utilti = Utiles()
    val viewModel = ViewModelSignUp()
    val action = SignUpActionsListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting
       val ui = utilti.settingSignUp(this,this@SignUpActivity)
       action.signUp(ui,viewModel,this)
       action.openLogin(ui,this)
    }








}