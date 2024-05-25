package com.example.testchatapp.featuer_chat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.databinding.ItemReciverBinding
import com.example.testchatapp.databinding.ItemReciverBinding.inflate as reciverInflate

import com.example.testchatapp.databinding.ItemSenderBinding
import com.example.testchatapp.databinding.ItemSenderBinding.inflate as senderInflate

import com.example.testchatapp.featuer_chat.domain.models.Message
import com.google.firebase.auth.FirebaseAuth

class MessengerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val SENDER_TYPE = 1
    private val RECIVER_TYPE = 2

    lateinit var messegeList: MutableLiveData<ArrayList<Message>>

    inner class ReciverViewHolder(val reciverView: ItemReciverBinding) :
        RecyclerView.ViewHolder(reciverView.root)

    inner class SenderViewHolder(val sendrView: ItemSenderBinding) :
        RecyclerView.ViewHolder(sendrView.root)

    private fun checkViewType(position: Int): Boolean {
        return messegeList.value!![position].userId == FirebaseAuth.getInstance().uid
    }

    override fun getItemViewType(position: Int): Int {
        return if (checkViewType(position)) {
            SENDER_TYPE
        } else
            RECIVER_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == SENDER_TYPE) {

            val ly = LayoutInflater.from(parent.context)
            val view = senderInflate(ly)
            SenderViewHolder(view)
        } else {
            val ly = LayoutInflater.from(parent.context)
            val view = reciverInflate(ly)
            ReciverViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(messegeList.value!![position]) {
            if (holder::class == SenderViewHolder::class) {
                val senderHolder = holder as SenderViewHolder
                senderHolder.sendrView.msgSender.text = message
                senderHolder.sendrView.msgTimeSender.text = messageTime
            } else {
                val reciverHolder = holder as ReciverViewHolder
                reciverHolder.reciverView.textReceiverMsg.text = message
                reciverHolder.reciverView.receiverTimeMsg.text = messageTime
            }
        }

    }

    override fun getItemCount(): Int {
        return messegeList.value!!.size
    }


}