package com.example.testchatapp.feature_authetication.data.firebase_source

import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FireBaseListeners {

    companion object{
        var isFirstUserExists = false
      fun  isFirstUserExistsListener(onDataChange:()-> Unit = {}):ValueEventListener{

            val listener = object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    if (snapshot.children.first().exists())
                        isFirstUserExists = true
                    println("========================> done check the isFirstUserExists =  $isFirstUserExists ")
                    onDataChange()
                }

                override fun onCancelled(error: DatabaseError) {
                    println(error)
                }

            }
            return  listener
        }

        fun unFriendListListener(): ValueEventListener {
            val listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userId = UtilsReference.user.id
                    val isFirstUser = snapshot.children.first().exists()
                    if (isFirstUser) {
                        snapshot.children.forEach {
                            val userUnfriendDb = it.getValue(Users::class.java)
                            val idUnfriend = userUnfriendDb!!.id
                            val userNameUnfriend = userUnfriendDb.userName

                            UtilsReference.unFriendUser.userId = userId
                            UtilsReference.unFriendUser.userUnfriendId = idUnfriend
                            UtilsReference.unFriendUser.userUnfriendUserName = userNameUnfriend
                            UtilsReference.mutableUsersUnFriendsList.value!!.add(UtilsReference.unFriendUser)
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    println(error)
                }

            }
            return listener
        }
    }


}