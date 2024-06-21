package com.example.testchatapp.feature_authetication.presentation.fragments.login_fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.testchatapp.R
import com.example.testchatapp.databinding.FragmentLoginBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity.SignUpActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginFragmentListener {

        private lateinit var auth: FirebaseAuth
        fun login(ui: FragmentLoginBinding,
                  activity: Context,
                  modelLogin: LoginViewModel,
        ) {

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
            ui: FragmentLoginBinding,
        ) {

            res.addOnCompleteListener {

                if (it.isSuccessful) {
                    println("Done login with correct information")
                    hidePrograssBsar(ui)


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


        private fun getUserText(ui: FragmentLoginBinding): Pair<String, String> {
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

        fun cleanText(ui: FragmentLoginBinding) {
            ui.emailTextBox.setText("")
            ui.passwordTextBox.setText("")
        }


        fun goUserChatList(context: Context) {
            val intent = Intent(context, UsersChatRoomListActivity::class.java)
            context.startActivity(intent)
        }




        fun showProgressBar(ui: FragmentLoginBinding) {
            ui.progressBarLog.visibility = View.VISIBLE
        }

        fun hidePrograssBsar(ui: FragmentLoginBinding) {
            ui.progressBarLog.visibility = View.INVISIBLE

        }

        fun openSignUp(context: Context, ui: FragmentLoginBinding) {
            ui.btnSignUp.setOnClickListener {
                ui.rootLy.findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
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
