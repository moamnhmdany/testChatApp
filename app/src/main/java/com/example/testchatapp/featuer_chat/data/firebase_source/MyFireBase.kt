package com.example.testchatapp.featuer_chat.data.firebase_source

import android.net.Uri
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.tasks.await


class MyFireBase : DataBaseDao {
    private val usersRef = Firebase.database.reference.child("Users")
    private val chatsRef = Firebase.database.reference.child("chats")
    private  val chatsSoundRef = FirebaseStorage.getInstance().getReference("sound")

    private var usersFriendListRef = Firebase.database.reference.child("UserFriends")

    override suspend fun getAllUsers(listener: ValueEventListener){
        usersRef.addValueEventListener(listener)
        println("====================> done get snap shoot from firebase list of users  ")

    }


    override suspend fun saveFriendUser(userId: String, friendUser: UsersUnfriend) {
        usersRef.child(FirebaseAuth.getInstance().uid.toString()).child("UserFriends")
            .child(friendUser.userUnfriendId).setValue(friendUser).await()

         usersRef.child(FirebaseAuth.getInstance().uid.toString()).get().addOnSuccessListener {
            val data = it.getValue(Users::class.java)
             val id = friendUser.userUnfriendId
             friendUser.userId= friendUser.userUnfriendId
             friendUser.userUnfriendUserName = data!!.userName
             friendUser.userUnfriendId = data.id
             friendUser.userUnfriendImageUri = data.imageUri

             usersRef.child(id).child("UserFriends")
                 .child(FirebaseAuth.getInstance().uid.toString()).setValue(friendUser)
        }
    }

    override suspend fun getUserFriendsList(listener: ValueEventListener){
       usersRef.child(FirebaseAuth.getInstance().uid.toString())
            .child("UserFriends").addValueEventListener(listener)

    }


    override suspend fun sendMessage(roomId: String, msgId: String, msg:Message){
        chatsRef.child(roomId).child(msgId).setValue(msg).await()
    }
    override suspend fun getMessages(roomId : String, listener: ValueEventListener) {

        chatsRef.child(roomId).addValueEventListener(listener)
       println("-----------------> getMessages fun in firebase  is done running")

    }


 override  fun getUserData(){
      usersRef.child(FirebaseAuth.getInstance().uid.toString()).get().addOnSuccessListener {
          UtilsReference.user = it.getValue(Users::class.java)!!
          UtilsReference.userMutableliveData.postValue(UtilsReference.user)
      }
   }

    override suspend fun uploadSoundRecord(uri: Uri, listener: OnSuccessListener<UploadTask.TaskSnapshot>){
       println("-----------------the audio path in firebase to upload> $uri")
        chatsSoundRef.child(FirebaseAuth.getInstance().uid.toString()).putFile(Uri.fromFile(UtilsReference.audioFile)).addOnSuccessListener(listener)
    }

    override suspend fun addSoundUri(roomId: String, msgId: String,msg:Message, listener: OnSuccessListener<Any>){
        chatsRef.child(roomId).child(msgId).setValue(msg.soundUri).addOnSuccessListener(listener)
    }
}

