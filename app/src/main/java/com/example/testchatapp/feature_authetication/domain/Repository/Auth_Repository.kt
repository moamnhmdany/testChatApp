package com.example.testchatapp.feature_authetication.domain.Repository

import android.content.Context
import android.net.Uri
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.storage.UploadTask

interface Auth_Repository {

    suspend fun createNewUserWithEmail(user: Users, context: Context)
    fun loginWithGoogle(user:Users)
    fun loginWithTwitter(user:Users)
    fun lginWithFacebook(user: Users)
   suspend  fun loginWithEmail(user: Users,context: Context): Task<AuthResult>
    fun getUsers():ArrayList<Users>
    fun uploadImage(uri: Uri, listener: OnSuccessListener<UploadTask.TaskSnapshot>)
    suspend  fun addImageUri()
}