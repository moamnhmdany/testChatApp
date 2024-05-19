package com.example.testchatapp.feature_authetication.domain.use_case

import android.content.Context
import android.content.Intent
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class UserOpareations {

    companion object{


        fun checkUserState(context: Context){

             val userDataBase: FirebaseAuth = Firebase.auth

             if(userDataBase.currentUser != null)
             {
                 val intent = Intent(context, UsersChatRoomListActivity::class.java)
                 context.startActivity(intent)
             }
         }
     }

}