package com.example.testchatapp.feature_authetication.presentation.util
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.WindowCompat
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.databinding.ActivitySignUpBinding
import com.example.testchatapp.databinding.ActivitySignUpBinding.inflate
import com.example.testchatapp.databinding.ActivityLoginScreenBinding
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.databinding.ActivityUsersListAddBinding

import com.example.testchatapp.databinding.ActivityUsersChatListBinding.inflate as inflateUsers
import com.example.testchatapp.databinding.ActivityLoginScreenBinding.inflate as inflateLogin
import com.example.testchatapp.databinding.ActivityUsersListAddBinding.inflate as inflateListAddFriend
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding.inflate as inflateChatMessangerPage



class Utiles() {
    private  lateinit var binding: ActivitySignUpBinding
    private  lateinit var bindingLogin: ActivityLoginScreenBinding
    private  lateinit var bindingUsersList: ActivityUsersChatListBinding
    private  lateinit var bindingUsersListAdd: ActivityUsersListAddBinding
    private lateinit var bindingChatMessangrePage: ActivityChatMessangerPageBinding


    fun settingSignUp(context :Context,activity: Activity): ActivitySignUpBinding {
        val li = LayoutInflater.from(context)
        binding = inflate(li)
        val view = binding.root

        activity.setContentView(view)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
       return binding
    }
    fun settingLogin(context :Context,activity: Activity):ActivityLoginScreenBinding{
        val li = LayoutInflater.from(context)
        bindingLogin = inflateLogin(li)
        val view = bindingLogin.root
        activity.setContentView(view)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
         return  bindingLogin
    }

    fun settingUserList(context :Context,activity: Activity):ActivityUsersChatListBinding{
        val li = LayoutInflater.from(context)
        bindingUsersList = inflateUsers(li)
        val view = bindingUsersList.root
        activity.setContentView(view)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        return  bindingUsersList
    }
    fun settingUserListAddFriend(context :Context,activity: Activity):ActivityUsersListAddBinding{
        val li = LayoutInflater.from(context)
        bindingUsersListAdd = inflateListAddFriend(li)
        val view = bindingUsersListAdd.root
        activity.setContentView(view)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        return  bindingUsersListAdd
    }

    fun settingChatMessangerPage(context: Context,activity: Activity):ActivityChatMessangerPageBinding{
        val li = LayoutInflater.from(context)
        bindingChatMessangrePage = inflateChatMessangerPage(li)
        val view = bindingChatMessangrePage.root
        activity.setContentView(view)
        WindowCompat.setDecorFitsSystemWindows(activity.window, false)
        return  bindingChatMessangrePage
    }

}