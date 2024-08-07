package com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.adapters.ChatFriend
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class UsersListAddActivity : AppCompatActivity(), ChatFriend {
    val setting = Utiles()
    val actions = AddFriendListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val ui = setting.settingUserListAddFriend(this,this)

        actions.observeUsers(this,ui,this)
        actions.goback(this,ui)
        UtilsReference.interfaceChat = this@UsersListAddActivity


    }




    override fun onStop() {
        super.onStop()
        if (UtilsReference.mutableUsersUnFriendsList!=null){
        UtilsReference.mutableUsersUnFriendsList.value!!.clear()
        }

    }

    override fun openChat(usersUnfriendData: UsersUnfriend) {
        println("--------------user chat data= ${usersUnfriendData.userUnfriendUserName}")
        actions.goToChat(this,usersUnfriendData)
    }

}
