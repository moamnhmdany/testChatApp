package com.example.testchatapp.featuer_chat.domain.use_case

import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UseCaseGetAllUsers {

    private var repo: ChatRepository = RepositoryImpl()
    suspend fun getAllUsers():Iterable<DataSnapshot>{
       return  repo.getAllUsers()

    }



     suspend fun  setupUersUnfriendList(){
        val dataSnapshot =  getAllUsers()
         dataSnapshot.forEach {
             runBlocking(Dispatchers.Main){
                 launch {

                     val user =   it.getValue(Users::class.java)
                     UtilsReference.mutableUsersUnFriendsList.value = UtilsReference.listUsersUnfriends

                     val unfriend = makeUnfriendObject(user)
                     if (notSame(user!!.id)){
                         UtilsReference.mutableUsersUnFriendsList.value!!.add(unfriend)

                     }
              }
             }

         }
      }

    private fun makeUnfriendObject(user: Users?) :UsersUnfriend{
        val id = user?.id
        val userName = user?.userName
        val unFriend = UsersUnfriend()

        unFriend.userUnfriendId = id!!
        unFriend.userUnfriendUserName = userName!!
        return unFriend
    }

   private fun notSame(id:String):Boolean{
     return  id != FirebaseAuth.getInstance().uid
   }
}