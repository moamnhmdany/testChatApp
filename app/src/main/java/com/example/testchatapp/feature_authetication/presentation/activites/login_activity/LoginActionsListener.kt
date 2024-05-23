package com.example.testchatapp.feature_authetication.presentation.activites.login_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.testchatapp.databinding.ActivityLoginScreenBinding
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity.SignUpActivity
import com.google.firebase.auth.FirebaseAuth
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

class LoginActionsListener {
    private lateinit var auth: FirebaseAuth
    fun login(ui: ActivityLoginScreenBinding, activity: Context, modelLogin: ViewModelLogin) {

        ui.btnSingIn.setOnClickListener {
            val (email, pass) = getUserText(ui)
            val user = getUser(email, pass)
            val results = modelLogin.loginUser(activity, user)
            showProgressBar(ui)
            processResults(results, activity, ui)

        }
    }

    private fun processResults(
        res: Task<AuthResult>,
        context: Context,
        ui: ActivityLoginScreenBinding
    ) {

        res.addOnCompleteListener {

            if (it.isSuccessful) {
                println("Done login with correct information")
                hidePrograssBsar(ui)
                goUserChatList(context)
            } else {
                println("failed login ${it.exception}")
                hidePrograssBsar(ui)
                makeToast(context)
            }

        }

    }

    fun View.hideKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun makeToast(context: Context) {
        Toast.makeText(context, "please write your information in correct way", Toast.LENGTH_SHORT)
            .show()
    }


    private fun getUserText(ui: ActivityLoginScreenBinding): Pair<String, String> {
        val userEmail = ui.emailTextBox.text.toString()
        val password = ui.passwordTextBox.text.toString()

        return Pair(userEmail, password)
    }

    private fun getUser(email: String, password: String): Users {
        val user = UtilsReference.user
        user.email = email
        user.password = password
        return user
    }

    fun cleanText(ui: ActivityLoginScreenBinding) {
        ui.emailTextBox.setText("")
        ui.passwordTextBox.setText("")
    }


    fun goUserChatList(context: Context) {
        val intent = Intent(context, UsersChatRoomListActivity::class.java)
        context.startActivity(intent)


    }

    fun showProgressBar(ui: ActivityLoginScreenBinding) {
        ui.progressBarLog.visibility = View.VISIBLE
    }

    fun hidePrograssBsar(ui: ActivityLoginScreenBinding) {
        ui.progressBarLog.visibility = View.INVISIBLE

    }

    fun openSignUp(context: Context, ui: ActivityLoginScreenBinding) {
        ui.btnSignUp.setOnClickListener {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun checkUser(context: Activity) {
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            context.startActivity(Intent(context, UsersChatRoomListActivity::class.java))
            context.finish()
        }
    }

}