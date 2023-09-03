package com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.UsersListAddActivity

class UsersListListenre {
    lateinit var model: ViewModelUsersList
    val listAdapter = UtilsReference.chatListAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val  usersList = UtilsReference.list

    fun observeUsers(owner: ViewModelStoreOwner,  context: Context, ui: ActivityUsersChatListBinding) {
        val model = ViewModelProvider(owner)[ViewModelUsersList::class.java]
        recycleViewSetting(context, ui)
        model.getUsers(usersList)
        model.update(ui)
    }

    private fun recycleViewSetting(context: Context, ui: ActivityUsersChatListBinding) {
        layoutManager = LinearLayoutManager(context)
        listAdapter.userList = usersList
        ui.usersChatListRecyclerview.layoutManager = layoutManager
        ui.usersChatListRecyclerview.adapter = listAdapter
    }

    fun addFriend(ui: ActivityUsersChatListBinding,context:Context) {
        ui.btnAddFriend.setOnClickListener {
            val intent = Intent(context, UsersListAddActivity::class.java)
            context.startActivity(intent)
        }
    }


companion object{
    fun showProgrecceBar(ui: ActivityUsersChatListBinding){
        ui.usersListProgressBar.visibility = View.VISIBLE
    }
    fun hideProgrecceBar(ui: ActivityUsersChatListBinding){
        ui.usersListProgressBar.visibility = View.INVISIBLE

    }

}

}

