package com.example.testchatapp.feature_authetication.presentation.fragments.sign_up_fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.navigation.findNavController
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivitySignUpBinding
import com.example.testchatapp.databinding.FragmentLoginBinding
import com.example.testchatapp.databinding.FragmentSignUpBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.presentation.activites.login_activity.LoginActivity
import com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity.ViewModelSignUp

class SignUpFragmentListener {


    private fun getUserText(ui: FragmentSignUpBinding): Triple<String, String, String> {
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
        return user
    }

    private fun cleanText(ui: FragmentSignUpBinding) {
        ui.nameTextBox.setText("")
        ui.emailTextBox.setText("")
        ui.passwordTextBox.setText("")
    }

    fun signUp(
        ui: FragmentSignUpBinding,
        model: SignUpViewModel,
        context: Context,
        activity: Activity
    ) {
        ui.btnSingIn.setOnClickListener {
            val (userName, email, password) = getUserText(ui)
            val user = getUser(userName, email, password)

            println("============> the email is activity is == ")
            println(email)

            ui.progressBar.visibility = View.VISIBLE
            model.createUser(user, context)
            cleanText(ui)
            ui.progressBar.visibility = View.INVISIBLE
            goProfile(ui)
           // goUserListPage(activity)
        }
    }

    fun openLogin(ui: FragmentSignUpBinding) {
        ui.btnSignUpBack.setOnClickListener {
            ui.rootLySignUp.findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }
    private fun goProfile(ui: FragmentSignUpBinding){
        ui.rootLySignUp.findNavController().navigate(R.id.action_signUpFragment_to_imageProfileFragment)
    }



}