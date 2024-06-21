package com.example.testchatapp.feature_authetication.data.firebase_source

import android.content.Context
import android.net.Uri
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.storage.UploadTask

interface MyDataBaseDao{


     suspend fun createUserWithEmailAndPassword(user: Users,context: Context)
     suspend fun  loginWithEmailFireBase(user: Users,context: Context): Task<AuthResult>
     fun uploadImage(uri: Uri, listener: OnSuccessListener<UploadTask.TaskSnapshot>)
     suspend  fun addImageUri()
}