package com.example.testchatapp.featuer_chat.domain.use_case

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.databinding.ActivityUsersListAddBinding
import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.repository.ChatRepository
import com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity.UsersListListenre
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.AddFriendListener
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UseCaseGetAllUsers {

    private var repo: ChatRepository = RepositoryImpl()
    suspend fun getAllUsers(){
         repo.getAllUsers()

    }

    companion object {
        private lateinit var database: DatabaseReference

        fun updateAddFriend( ui: ActivityUsersListAddBinding) {
            getDataBaseRefAndCleanList()
            AddFriendListener.showProgrecceBar(ui)
            val listener = getEventListener(ui)
            database.addValueEventListener(listener)
        }
       private fun getEventListener( ui: ActivityUsersListAddBinding): ValueEventListener {
            val fireBaseListener = object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.child("Users").children.forEach {
                        val user = it.getValue(Users::class.java)
                         if(!check(user!!))
                        UtilsReference.mutableUsersList.value!!.add(user)
                        UtilsReference.addFriendListAdapter.notifyDataSetChanged()
                    }
                    AddFriendListener.hideProgrecceBar(ui)
                    println("done add all users to lists of add new friends")
                }

                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }
            }
            return fireBaseListener
        }
       fun check(user:Users) :Boolean{
         return user.id  == FirebaseAuth.getInstance().uid


       }
        private fun getDataBaseRefAndCleanList() {
            database = Firebase.database.reference
            UtilsReference.mutableUsersList.value!!.clear()
        }
        fun updateUserList( ui: ActivityUsersChatListBinding) {
            getDataBaseRefAndCleanList()
            UsersListListenre.showProgrecceBar(ui)
            val listener = getEventListenerChat(ui)
            database.addValueEventListener(listener)
        }

       private fun getEventListenerChat(  ui: ActivityUsersChatListBinding): ValueEventListener {
            val fireBaseListener = object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    dataSnapshot.child("Users").children.forEach {
                        val user = it.getValue(Users::class.java)
                        println(user?.email)
                        UtilsReference.mutableUsersList.value!!.add(user!!)
                        UtilsReference.chatListAdapter.notifyDataSetChanged()
                    }

                    UsersListListenre.hideProgrecceBar(ui)
                }

                override fun onCancelled(error: DatabaseError) {
                    println("failed")
                }
            }
            return fireBaseListener
        }



    }


}