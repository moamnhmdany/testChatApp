package com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testchatapp.databinding.ActivityUsersListAddBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.ChatMessangerPage
import com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity.UsersChatListActivity

class AddFriendListener {
    private val addFriendAdapter = UtilsReference.addFriendListAdapter
    private val listUsers =        UtilsReference.mutableUsersUnFriendsList
    private val model =            UtilsReference.addFriendModel
    fun observeUsers(lifecycleOwner: LifecycleOwner,ui:ActivityUsersListAddBinding,context:Context){
        showProgrecceBar(ui)
        model.getUnfriendUsersDataBase()
        UtilsReference.mutableUsersUnFriendsList.observe(lifecycleOwner, Observer {
            recycleviewSetting(context,ui)
        })
        hideProgrecceBar(ui)
        UtilsReference.mutableUsersUnFriendsList.observe(lifecycleOwner, Observer {
            it.clear()
        })
    }

   private fun recycleviewSetting(context:Context,ui: ActivityUsersListAddBinding){
       val layoutManger = LinearLayoutManager(context)
       ui.usersAddList.layoutManager =  layoutManger
       addFriendAdapter.list = listUsers
       ui.usersAddList.adapter = addFriendAdapter
   }
    fun goback(context: Context,ui: ActivityUsersListAddBinding){
        ui.btnAddFriendBack.setOnClickListener {
            val intent = Intent(context, UsersChatListActivity::class.java)
            context.startActivity(intent)
        }


    }
    companion object{
        fun showProgrecceBar(ui: ActivityUsersListAddBinding){
            ui.usersListAddProgressBar.visibility = View.VISIBLE
        }
        fun hideProgrecceBar(ui: ActivityUsersListAddBinding){
               ui.usersListAddProgressBar.visibility = View.INVISIBLE
        }


    }

    fun goToChat(context: Context,id:String){
        val intent = Intent(context, ChatMessangerPage::class.java)
        intent.putExtra("receiverId",id)
        context.startActivity(intent)
    }

}