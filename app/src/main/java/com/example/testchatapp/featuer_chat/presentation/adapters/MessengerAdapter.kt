package com.example.testchatapp.featuer_chat.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.databinding.ItemReciverBinding
import com.example.testchatapp.databinding.ItemReciverBinding.inflate as reciverInflate

import com.example.testchatapp.databinding.ItemSenderBinding
import com.example.testchatapp.databinding.ItemSoundReciverBinding
import com.example.testchatapp.databinding.ItemSoundSenderBinding
import com.example.testchatapp.databinding.ItemSenderBinding.inflate as senderInflate
import com.example.testchatapp.databinding.ItemSoundSenderBinding.inflate as senderSoundInflate
import com.example.testchatapp.databinding.ItemSoundReciverBinding.inflate as receiverSoundInflate


import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.CustomsTimer
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.MyMediaPlayer
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MessengerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() ,
    CustomsTimer.OnTimerTickListener {
    private  var timerSenderSoundHolder: SenderSoundViewHolder? = null
    private  var receiverSoundHolder: ReceiverSoundViewHolder? = null
    private val adapterTimer = CustomsTimer(this)
    private val SENDER_TYPE = 1
    private val RECEIVER_TYPE = 2
    private val SENDER_SOUND_TYPE = 3
    private val RECEIVER_SOUND_TYPE = 4
    private var currentSoundUri = ""
    private var previousPosition = 5000
    lateinit var messegeList: MutableLiveData<ArrayList<Message>>

    inner class ReceiverViewHolder(val receiverView: ItemReciverBinding) :
        RecyclerView.ViewHolder(receiverView.root)

    inner class SenderViewHolder(val senderView: ItemSenderBinding) :
        RecyclerView.ViewHolder(senderView.root)

    inner class ReceiverSoundViewHolder(val receiverSoundView: ItemSoundReciverBinding) :
        RecyclerView.ViewHolder(receiverSoundView.root)

    inner class SenderSoundViewHolder(val senderSoundView: ItemSoundSenderBinding) :
        RecyclerView.ViewHolder(senderSoundView.root)

    private fun checkViewType(position: Int): Boolean {
        return messegeList.value!![position].userId == FirebaseAuth.getInstance().uid
    }

    private fun checkSoundViewType(position: Int): Boolean {
        return messegeList.value!![position].message == "soundMessage"
    }

    override fun getItemViewType(position: Int): Int {
        return if (checkViewType(position)) {
            if (checkSoundViewType(position)) {
                SENDER_SOUND_TYPE
            } else {
                SENDER_TYPE
            }

        } else {
            if (checkSoundViewType(position)) {
                RECEIVER_SOUND_TYPE
            } else {
                RECEIVER_TYPE
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            1 -> {
                val ly = LayoutInflater.from(parent.context)
                val view = senderInflate(ly)
                SenderViewHolder(view)
            }

            2 -> {
                val ly = LayoutInflater.from(parent.context)
                val view = reciverInflate(ly)
                ReceiverViewHolder(view)
            }

            3 -> {
                val ly = LayoutInflater.from(parent.context)
                val view = senderSoundInflate(ly)
                println("------------> onCreateViewHolder run again")

                SenderSoundViewHolder(view)

            }

            else -> {
                val ly = LayoutInflater.from(parent.context)
                val view = receiverSoundInflate(ly)
                ReceiverSoundViewHolder(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        println("------------> onBindViewHolder run again")
        UtilsReference.myTimer = adapterTimer
        with(messegeList.value!![position]) {
            when (holder) {

                is SenderViewHolder -> {
                    val senderHolder = holder as SenderViewHolder
                    senderHolder.senderView.msgSender.text = message
                    senderHolder.senderView.msgTimeSender.text = messageTime
                }

                is ReceiverViewHolder -> {
                    val reciverHolder = holder as ReceiverViewHolder
                    reciverHolder.receiverView.textReceiverMsg.text = message
                    reciverHolder.receiverView.receiverTimeMsg.text = messageTime
                }

                is SenderSoundViewHolder -> {
                    val  senderSoundHolder = holder as SenderSoundViewHolder


                    senderSoundHolder.senderSoundView.btnSend.setOnClickListener {
                        timerSenderSoundHolder = senderSoundHolder
                                 if(currentSoundUri != soundUri){
                                     currentSoundUri = soundUri
                                     UtilsReference.myTimer.stop()
                                     UtilsReference.customMediaPlayer!!.setupMediaPlayer(soundUri)
                                     setupMediaPlayerListener(){
                                         UtilsReference.myTimer.stop()
                                         senderSoundHolder!!.senderSoundView.btnSend
                                             .setBackgroundResource(R.drawable.play_icon)
                                     }
                          }
                        if (UtilsReference.isSoundRuning)
                        {
                            senderSoundHolder!!.senderSoundView.btnSend.setBackgroundResource(
                                R.drawable.play_icon)
                            println("------------uri= $soundUri")
                            UtilsReference.myTimer.pause()
                            UtilsReference.customMediaPlayer!!.pauseSound()
                        }

                        else if (UtilsReference.isSoundPause){
                            senderSoundHolder!!.senderSoundView.btnSend.setBackgroundResource(
                                R.drawable.pause_icon)
                            UtilsReference.myTimer.start()
                            UtilsReference.customMediaPlayer!!.resumeSound()
                        }

                        else if(UtilsReference.isSoundStop){

                            senderSoundHolder!!.senderSoundView.btnSend.setBackgroundResource(
                                R.drawable.pause_icon)
                           UtilsReference.myTimer.start()
                           UtilsReference.customMediaPlayer!!.startRunSound()
                       }
                    }
                }

                else -> {
                     receiverSoundHolder = holder as ReceiverSoundViewHolder
                    receiverSoundHolder!!.receiverSoundView.btnReceiver.setOnClickListener {

                    }
                }
            }
        }

    }



    override fun getItemCount(): Int {
        return messegeList.value!!.size
    }

    private fun setupMediaPlayerListener(onCompleteListener: ()->Unit) {
            UtilsReference.customMediaPlayer!!.onCompleteListener( onCompleteListener )
    }

    override fun onTimerTick(duration: String) {
        timerSenderSoundHolder!!.senderSoundView.tvVoiceTime.text = duration
    }




}