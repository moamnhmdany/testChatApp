package com.example.testchatapp.featuer_chat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.UserItemListBinding
import com.example.testchatapp.databinding.UserItemListBinding.inflate
import com.example.testchatapp.feature_authetication.domain.model.Users


class UserItemAdapter() : RecyclerView.Adapter<UserItemAdapter.ViewHolder>() {
    lateinit var userList: MutableLiveData<ArrayList<Users>>

    inner class ViewHolder(val ItemView: UserItemListBinding) :
        RecyclerView.ViewHolder(ItemView.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ly = LayoutInflater.from(parent.context)
        val view = inflate(ly)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.value!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(userList.value!![position]) {
                ItemView.userName.text = userName

            }
        }
    }


}