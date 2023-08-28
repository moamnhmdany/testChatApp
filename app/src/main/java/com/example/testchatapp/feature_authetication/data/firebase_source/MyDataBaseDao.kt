package com.example.testchatapp.feature_authetication.data.firebase_source

import android.content.Context
import com.example.testchatapp.feature_authetication.domain.model.Users

interface MyDataBaseDao{


     suspend fun createUserWithEmailAndPassword(user: Users,context: Context)
}