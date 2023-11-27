package com.example.testchatapp.feature_authetication.data.firebase_source

import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FireBaseListeners {

    companion object {
        var isFirstUserExists : DataSnapshot? = null


        fun unFriendListListener(snapshot:DataSnapshot) {


                           val userLogId = FirebaseAuth.getInstance().uid


                            val userUnfriendDb = snapshot.getValue(Users::class.java)

                            val idUnfriend = userUnfriendDb!!.id
                            val userNameUnfriend = userUnfriendDb.userName

                            println("============$userNameUnfriend")
                            val unFriend = UsersUnfriend()

                            unFriend.userId = UtilsReference.user.id
                            unFriend.userUnfriendId = idUnfriend
                            unFriend.userUnfriendUserName = userNameUnfriend

                            UtilsReference.mutableUsersUnFriendsList.value =
                                              UtilsReference.listUsersUnfriends

                           if (userLogId!=idUnfriend) {
                            println(UtilsReference.mutableUsersUnFriendsList.value)
                            UtilsReference.mutableUsersUnFriendsList.apply() {
                                this.value!!.add(unFriend)
                            }
                        }
                        println("========================finish  makeUnfriendList")

        }
    }


}