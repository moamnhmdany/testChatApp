package com.example.testchatapp.featuer_chat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.UserRoomShapBinding
import com.example.testchatapp.databinding.UserRoomShapBinding.inflate
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.squareup.picasso.Picasso

class UserRoomChatListAdapter(): RecyclerView.Adapter<UserRoomChatListAdapter.MyViewHolder>() {
      lateinit var roomList : MutableLiveData<ArrayList<UserChatRoom>>

    inner class MyViewHolder(val myView : UserRoomShapBinding):
        RecyclerView.ViewHolder(myView.root){


        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context)
        val view = inflate(layout)
        val myHolder = MyViewHolder(view)
        return myHolder

    }

    override fun getItemCount(): Int {

        return roomList.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            with(roomList.value!![position]){
                holder.myView.rootLy.setOnClickListener {
                 UtilsReference.interfaceChatRoom.goChat(this)
                }
                 holder.myView.userName.text = this.userName
                 holder.myView.lastMsg.text = this.userLastMsg
                if(userPictuer.isNotEmpty()){
                    Picasso.get().load(userPictuer).into(holder.myView.profileImageAddFriend)
                }
            }
        }
    }

}