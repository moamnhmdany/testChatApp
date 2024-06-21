package com.example.testchatapp.feature_authetication.data.repository

import android.content.Context
import android.net.Uri
import com.example.testchatapp.feature_authetication.data.firebase_source.FireeBase
import com.example.testchatapp.feature_authetication.data.firebase_source.MyDataBaseDao
import com.example.testchatapp.feature_authetication.domain.Repository.Auth_Repository
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.storage.UploadTask

class ChatRepositoryImpl(context: Context) :Auth_Repository{

      private val myDataBaseDao : MyDataBaseDao = FireeBase(context)
    override suspend fun createNewUserWithEmail(user: Users,context: Context) {

             myDataBaseDao.createUserWithEmailAndPassword(user,context)


    }

    override fun loginWithGoogle(user: Users) {
    }

    override fun loginWithTwitter(user: Users) {
        TODO("Not yet implemented")
    }

    override fun lginWithFacebook(user: Users) {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithEmail(user: Users, context: Context):Task<AuthResult> {
      return  myDataBaseDao.loginWithEmailFireBase(user,context)
    }


    override fun getUsers(): ArrayList<Users> {
        TODO("Not yet implemented")
    }
  override suspend  fun uploadImage(uri: Uri, listener: OnSuccessListener<UploadTask.TaskSnapshot>){
        myDataBaseDao.uploadImage(uri, listener)
    }
  override  suspend  fun addImageUri(listener: OnSuccessListener<Any>){
        myDataBaseDao.addImageUri(listener)
    }
}