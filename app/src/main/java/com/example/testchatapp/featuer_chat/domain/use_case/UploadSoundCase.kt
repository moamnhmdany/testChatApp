package com.example.testchatapp.featuer_chat.domain.use_case

import android.net.Uri
import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UploadSoundCase(private val repo: RepositoryImpl) {


    suspend fun uploadSound(listener: OnSuccessListener<Any>) {
        setupUri()
        if (UtilsReference.soundUri != null) {

            repo.uploadSoundRecord(UtilsReference.soundUri!!) { task ->

                task.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                    runBlocking(Dispatchers.IO) {
                        UtilsReference.msg.soundUri = it.toString()
                        repo.addSoundUri("", "", UtilsReference.msg, listener)
                    }
                }
            }
        }
    }


    private fun setupUri() {
        if (UtilsReference.audioPath.isNotEmpty()) {
            UtilsReference.soundUri = Uri.parse(UtilsReference.audioPath)
        }
    }
}