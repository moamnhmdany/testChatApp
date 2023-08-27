package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.databinding.ActivitySignUpBinding
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.domain.use_case.UserOpareations
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class SignUpActivity : AppCompatActivity() {
    val utilti = Utiles()
    val viewModel = ViewModel_SignUp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting()
       val ui = utilti.settingSignUp(this,this@SignUpActivity)


        ui.btnSingIn.setOnClickListener {
            val (userName, email, password) = getUserText(ui)
            val user =  getUser(userName, email, password)
            println("============> the email is activity is == ")
            println(email)
            viewModel.createUser(user)
        }

    }

    private fun getUserText(ui: ActivitySignUpBinding): Triple<String, String, String> {
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

}