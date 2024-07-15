package com.example.testchatapp.featuer_chat.data.firebase_source

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.UploadTask

interface DataBaseDao {

    suspend fun getAllUsers(listener: ValueEventListener)
    suspend fun saveFriendUser(userId:String,friendUser: UsersUnfriend)
    suspend fun getUserFriendsList(listener: ValueEventListener)
    suspend fun sendMessage(roomId: String, msgId: String, msg: Message)
    suspend fun getMessages(roomId : String, listener: ValueEventListener)
    suspend fun uploadSoundRecord(uri: Uri, listener: OnSuccessListener<UploadTask.TaskSnapshot>)
    suspend fun addSoundUri(roomId: String, msgId: String,msg:Message, listener: OnSuccessListener<Any>)
    fun getUserData()

}