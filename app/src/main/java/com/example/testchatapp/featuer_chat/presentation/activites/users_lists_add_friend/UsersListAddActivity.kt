package com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.testchatapp.feature_authetication.presentation.util.Utiles
import kotlinx.coroutines.launch

class UsersListAddActivity : AppCompatActivity() {
    val setting = Utiles()
    val actions = AddFriendListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val ui = setting.settingUserListAddFriend(this,this)

        actions.observeUsers(this,ui,this)
        actions.goback(this,ui)
        
    }
}
