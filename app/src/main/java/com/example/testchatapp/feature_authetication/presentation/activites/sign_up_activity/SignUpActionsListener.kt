package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import com.example.testchatapp.databinding.ActivitySignUpBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity.UsersChatListActivity
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.domain.use_case.UserOpareations
import com.example.testchatapp.feature_authetication.presentation.activites.login_activity.LoginActivity

class SignUpActionsListener {


     private fun getUserText(ui: ActivitySignUpBinding): Triple<String, String, String> {
        val userName = ui.nameTextBox.text.toString()
        val email = ui.emailTextBox.text.toString()
        val password = ui.passwordTextBox.text.toString()
        println("============================getUserText eamil is = ")
        println(email)
        return Triple(userName, email, password)
    }
    private fun getUser(userName: String, email: String, password: String): Users {
        val user = UtilsReference.user
        user.userName = userName
        user.email = email
        user.password = password
//        user.listUserFriends.value = UtilsReference.listUserFriends
//        user.listUserUnFriends.value = UtilsReference.listUserUnfriends
        return  user
    }
    private fun cleanText(ui: ActivitySignUpBinding){
        ui.nameTextBox.setText("")
        ui.emailTextBox.setText("")
        ui.passwordTextBox.setText("")
    }

    fun signUp(ui: ActivitySignUpBinding ,model: ViewModelSignUp,context: Context ,activity: Activity){
        ui.btnSingIn.setOnClickListener {
            val (userName, email, password) = getUserText(ui)
            val user = getUser(userName, email, password)

            println("============> the email is activity is == ")
            println(email)

            ui.progressBar.visibility = View.VISIBLE
            model.createUser(user,context)
            cleanText(ui)
            ui.progressBar.visibility = View.INVISIBLE
            goUserListPage(activity)
        }
    }

    fun openLogin(ui: ActivitySignUpBinding,context: Context){
        ui.btnSignUpBack.setOnClickListener {
            val intent = Intent(context,LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
      private fun goUserListPage(activity: Activity){
          val intent = Intent(activity,UsersChatListActivity::class.java)
          activity.startActivity(intent)

      }

}