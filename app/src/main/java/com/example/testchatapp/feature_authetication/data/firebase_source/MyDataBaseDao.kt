package com.example.testchatapp.feature_authetication.data.firebase_source

import android.content.Context
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface MyDataBaseDao{


     suspend fun createUserWithEmailAndPassword(user: Users,context: Context)
     suspend fun  loginWithEmailFireBase(user: Users,context: Context): Task<AuthResult>
}