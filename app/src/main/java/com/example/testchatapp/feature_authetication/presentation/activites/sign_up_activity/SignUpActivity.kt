package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.presentation.util.Utiles
import android.widget.Button
import android.widget.Toast
import com.example.testchatapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    val utilti = Utiles()
    val viewModel = ViewModel_SignUp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting()
       val ui = utilti.settingSignUp(this,this@SignUpActivity)
        val (userName, email, password) = getUserText(ui)
      val user =  getUser(userName, password, email)

        ui.btnSingIn.setOnClickListener {
            Toast.makeText(this@SignUpActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }

           viewModel.usersLiveData.observe(this, Observer {
               it.add(user)
           })
    }

    private fun getUserText(ui: ActivitySignUpBinding): Triple<String, String, String> {
        val userName = ui.nameTextBox.text.toString()
        val email = ui.emailTextBox.text.toString()
        val password = ui.passwordTextBox.text.toString()
        return Triple(userName, email, password)
    }

     fun getUser(userName: String, password: String, email: String): Users {
        val user = Users().apply() {
            this.userName = userName
            this.Password = password
            this.Email = email
        }
        return  user
    }

}