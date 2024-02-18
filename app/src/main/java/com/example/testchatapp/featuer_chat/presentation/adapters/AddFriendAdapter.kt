package com.example.testchatapp.featuer_chat.presentation.adapters

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.UserItemAddFriendBinding
import com.example.testchatapp.databinding.UserItemAddFriendBinding.inflate
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.testchatapp.R


class AddFriendAdapter : RecyclerView.Adapter<AddFriendAdapter.MyViewHolder>() {
    lateinit var list: MutableLiveData<ArrayList<UsersUnfriend>>

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
                holder.MyView.tvUserName.text = userUnfriendUserName
                 MyView.btnImageAddFriend

                holder.MyView.btnImageAddFriend.setImageResource(R.drawable.chat)
                holder.MyView.btnImageAddFriend.setOnClickListener {
                    removeItem(adapterPosition)
                    println("==================$userUnfriendUserName")

                        save(this.userId,this)
                }
            }
        }

    }
        @OptIn(DelicateCoroutinesApi::class)
        fun save(userId:String, Frined:UsersUnfriend){
            GlobalScope.launch (Dispatchers.IO){

                    UtilsReference.userFriends.saveFriend(userId, Frined)

            }
            println("=============> done save frinde user")
        }
      fun removeItem(postion:Int){
          list.value!!.removeAt(postion)
          notifyItemRemoved(postion)
      }

}