package com.example.testchatapp.featuer_chat.domain.use_case

import android.util.Log
import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.models.UserFriends
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

const val TAG = "UseCaseGetAllUsers"

class UseCaseGetAllUsers {

    private var repo: ChatRepository = RepositoryImpl()
    suspend fun getAllUsers(listener: ValueEventListener) {
        repo.getAllUsers(listener)

    }


    suspend fun setupUersUnfriendList() {
        getAllUsers(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                UtilsReference.userFriendList.clear()
                UtilsReference.mutableUsersUnFriendsList.value?.clear()

                   snapshot.child(FirebaseAuth.getInstance().uid.toString())
                       .child("UserFriends").children.forEach {
                         if (it.exists()){
                       val userUnFriend = it.getValue(UsersUnfriend::class.java)
                           val userFriend = UserFriends()
                               userFriend.id = userUnFriend!!.userUnfriendId
                               userFriend.userName = userUnFriend.userUnfriendUserName

                              UtilsReference.userFriendList.add(userFriend)
                         }
                   }


                snapshot.children.forEach {

                    val user = it.getValue(Users::class.java)

                    UtilsReference.mutableUsersUnFriendsList.value =
                                            UtilsReference.listUsersUnfriends
                    val unfriend = makeUnfriendObject(user)

                    if (notSame(user!!.id)) {
                             if(check(user.id)) {
                                 UtilsReference.mutableUsersUnFriendsList.value!!.add(unfriend)
                             }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("--------------> get all users cancelled")
            }
        })

        println("=================> finish setupUersUnfriendList")
    }
  private fun check(id: String):Boolean{
      var checkState = true
      UtilsReference.userFriendList.forEach { userFriend ->
          checkState = userFriend.id != id
          if (!checkState){
              return false
          }
      }
      return checkState
  }



private fun makeUnfriendObject(user: Users?): UsersUnfriend {
    val unFriend = UsersUnfriend()

    val id = user?.id
    val userName = user?.userName

    unFriend.userUnfriendId = id!!
    unFriend.userUnfriendUserName = userName!!
    unFriend.userId = FirebaseAuth.getInstance().uid!!
    return unFriend
}

private fun notSame(id: String): Boolean {
    return id != FirebaseAuth.getInstance().uid
}

}
