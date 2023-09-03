package com.example.testchatapp.featuer_chat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.UserItemAddFriendBinding
import com.example.testchatapp.databinding.UserItemAddFriendBinding.inflate
import com.example.testchatapp.feature_authetication.domain.model.Users

class AddFriendAdapter : RecyclerView.Adapter<AddFriendAdapter.ViewHolder>() {
    lateinit var list: ArrayList<Users>
    inner  class  ViewHolder(val MyView :UserItemAddFriendBinding):RecyclerView.ViewHolder(MyView.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ly = LayoutInflater.from(parent.context)
        val view = inflate(ly)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            with(list[position]){
                holder.MyView.tvUserName.text = userName
            }
        }
    }
}