package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.databinding.ActivitySignUpBinding
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.domain.use_case.UserOpareations
import com.example.testchatapp.feature_authetication.presentation.activites.login_activity.LoginActivityScreen

class SignUpActionsListener() {


     fun getUserText(ui: ActivitySignUpBinding): Triple<String, String, String> {
        val userName = ui.nameTextBox.text.toString()
        val email = ui.emailTextBox.text.toString()
        val password = ui.passwordTextBox.text.toString()
        println("============================getUserText eamil is = ")
        println(email)
        return Triple(userName, email, password)
    }
    fun getUser(userName: String,  email: String,password: String): Users {
        val user = UserOpareations.newUser()
        user.userName = userName
        user.email = email
        user.password = password

        return  user
    }
    fun cleanText(ui: ActivitySignUpBinding){
        ui.nameTextBox.setText("")
        ui.emailTextBox.setText("")
        ui.passwordTextBox.setText("")
    }

    fun signUp(ui: ActivitySignUpBinding ,model: ViewModelSignUp,context: Context){
        ui.btnSingIn.setOnClickListener {
            val (userName, email, password) = getUserText(ui)
            val user = getUser(userName, email, password)

            println("============> the email is activity is == ")
            println(email)

            ui.progressBar.visibility = View.VISIBLE
            model.createUser(user,context)
            cleanText(ui)
            ui.progressBar.visibility = View.INVISIBLE
        }
    }

    fun openLogin(ui: ActivitySignUpBinding,context: Context){
        ui.btnSignUpBack.setOnClickListener {
            val intent = Intent(context,LoginActivityScreen::class.java)
            context.startActivity(intent)
        }
    }

}