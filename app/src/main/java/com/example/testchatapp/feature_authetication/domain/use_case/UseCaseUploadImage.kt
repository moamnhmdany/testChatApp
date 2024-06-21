package com.example.testchatapp.feature_authetication.domain.use_case

import android.content.Context
import android.net.Uri
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.data.repository.ChatRepositoryImpl
import com.example.testchatapp.feature_authetication.domain.Repository.Auth_Repository
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UseCaseUploadImage(context: Context) {

  val repo : Auth_Repository = ChatRepositoryImpl(context)



    fun uploadImage(uri: Uri,listener: OnSuccessListener<Any>){
        runBlocking (Dispatchers.IO){
            repo.uploadImage(uri) { task ->
                task!!.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                    UtilsReference.user.imageUri = it.toString()
                    runBlocking(Dispatchers.IO) {
                        repo.addImageUri(listener)
                    }
                }
            }
        }
  }





}