package com.example.testchatapp.featuer_chat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.UserItemAddFriendBinding
import com.example.testchatapp.databinding.UserItemAddFriendBinding.inflate
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users

class AddFriendAdapter : RecyclerView.Adapter<AddFriendAdapter.MyViewHolder>() {
    lateinit var list: MutableLiveData<ArrayList<Users>>

    inner class MyViewHolder(val MyView: UserItemAddFriendBinding) :
        RecyclerView.ViewHolder(MyView.root) {


           fun getMyPosition():Int{
              return adapterPosition
          }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val ly = LayoutInflater.from(parent.context)
        val view = inflate(ly)
        val myHolder = MyViewHolder(view)
        return myHolder
    }

    override fun getItemCount(): Int {
        return list.value!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(list.value!![position]) {
                holder.MyView.tvUserName.text = userName
                holder.MyView.btnImageAddFriend.setOnClickListener {
                    removeItem(adapterPosition)
                    println("==================$adapterPosition")

                }
            }
        }

    }

      fun removeItem(postion:Int){
          list.value!!.removeAt(postion)
          notifyItemRemoved(postion)
      }
    fun notifyAt(postion: Int){

    }
}