package com.example.testchatapp.feature_authetication.presentation.fragments.login_fragment

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.domain.use_case.UseCaseUserLogin
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(context: Context) : ViewModel() {
    val data = MutableLiveData<Users>()
    private val userCase =   UseCaseUserLogin(context)
    private lateinit var   check : Task<AuthResult>

    fun loginUser(context: Context, user: Users): Task<AuthResult> {
        data.value = user

        val job =   viewModelScope.launch(Dispatchers.IO){
            check = userCase.login(user, context)

        }
        runBlocking{
            job.join()

        }


        return check
    }
}