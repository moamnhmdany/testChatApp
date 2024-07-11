package com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testchatapp.databinding.ActivityUsersListAddBinding
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.ChatMessengerActivity
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.featuer_chat.presentation.adapters.AddFriendAdapter

class AddFriendListener {
         var addFriendAdapter : AddFriendAdapter? =null
          var listUsers : MutableLiveData<ArrayList<UsersUnfriend>>? = null
          var model : AddFriendModel? = null
    init {
        addFriendAdapter = UtilsReference.addFriendListAdapter
         listUsers =        UtilsReference.mutableUsersUnFriendsList
         model =            UtilsReference.addFriendModel
    }

    fun observeUsers(lifecycleOwner: LifecycleOwner,ui:ActivityUsersListAddBinding,context:Context){


        model?.getUnfriendUsersDataBase()
        UtilsReference.mutableUsersUnFriendsList.observe(lifecycleOwner, Observer {
            showProgrecceBar(ui)
                recycleViewSetting(context,ui)
            hideProgrecceBar(ui)

        })


    }

   private fun recycleViewSetting(context:Context, ui: ActivityUsersListAddBinding){
       val layoutManger = LinearLayoutManager(context)
       ui.usersAddList.layoutManager =  layoutManger
       addFriendAdapter?.list = listUsers!!
       ui.usersAddList.adapter = addFriendAdapter
   }

    fun goback(context: Context,ui: ActivityUsersListAddBinding){
        ui.btnAddFriendBack.setOnClickListener {
            val intent = Intent(context, UsersChatRoomListActivity::class.java)
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

    fun goToChat(context: Context,usersUnfriendData: UsersUnfriend){
        val intent = Intent(context, ChatMessengerActivity::class.java)
        val data = usersUnfriendData.userUnfriendUserName
        println("goChat-=-=-=--=-=-=-==-=-=-=$data")
        intent.putExtra("userDataFriend",usersUnfriendData)
        context.startActivity(intent)
    }

}

