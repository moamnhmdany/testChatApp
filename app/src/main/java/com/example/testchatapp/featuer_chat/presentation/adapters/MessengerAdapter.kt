package com.example.testchatapp.featuer_chat.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ItemReciverBinding
import com.example.testchatapp.databinding.ItemSenderBinding
import com.example.testchatapp.databinding.ItemSoundReciverBinding
import com.example.testchatapp.databinding.ItemSoundSenderBinding
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.CustomsTimer
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import com.example.testchatapp.databinding.ItemReciverBinding.inflate as reciverInflate
import com.example.testchatapp.databinding.ItemSenderBinding.inflate as senderInflate
import com.example.testchatapp.databinding.ItemSoundReciverBinding.inflate as receiverSoundInflate
import com.example.testchatapp.databinding.ItemSoundSenderBinding.inflate as senderSoundInflate

class MessengerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    CustomsTimer.OnTimerTickListener {
    private var timerSenderSoundHolder: SenderSoundViewHolder? = null
    private var receiverSoundHolder: ReceiverSoundViewHolder? = null
    private var percentage: Double = 0.0
    private var partNumber: Double = 0.0
    private var wholeNumber: Double = 0.0
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

        initializeTimer()
        with(messegeList.value!![position]) {
            when (holder) {

                is SenderViewHolder -> {
                    val senderHolder = holder as SenderViewHolder
                    senderHolder.senderView.msgSender.text = message
                    senderHolder.senderView.msgTimeSender.text = messageTime
                }

                is ReceiverViewHolder -> {
                    val receiverHolder = holder as ReceiverViewHolder
                    receiverHolder.receiverView.textReceiverMsg.text = message
                    receiverHolder.receiverView.receiverTimeMsg.text = messageTime
                }

                is SenderSoundViewHolder -> {
                    val senderSoundHolder = holder as SenderSoundViewHolder

                    setupWaveForm(senderSoundHolder)

                    senderSoundHolder.senderSoundView.btnSend.setOnClickListener {
                        timerSenderSoundHolder = senderSoundHolder
                        println("currentUri =$currentSoundUri")
                        println("soundUri = $soundUri")
                        if (currentSoundUri != soundUri) {

                            currentSoundUri = soundUri

                            println("---------------run setup if")

                            UtilsReference.myTimer.stop()
                            UtilsReference.customMediaPlayer!!.setupMediaPlayer(soundUri)

                            setupMediaPlayerListener( {
                                onStopListener(senderSoundHolder)
                            },{
                                senderSoundHolder.senderSoundView.btnSend
                                    .setBackgroundResource(R.drawable.play_icon)
                             }
                            )
                        }

                        if (UtilsReference.isSoundRuning) {


                            pauseSound(senderSoundHolder)

                        } else if (UtilsReference.isSoundPause) {
                            resumeSound(senderSoundHolder)
                        } else if (UtilsReference.isSoundStop) {
                            startSound(senderSoundHolder)
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

    private fun onStopListener(senderSoundHolder: SenderSoundViewHolder) {
        println("--------- setupMediaPlayerListener is running")
        UtilsReference.myTimer.stop()

        senderSoundHolder.senderSoundView.btnSend
            .setBackgroundResource(R.drawable.play_icon)

        senderSoundHolder.senderSoundView.tvVoiceTime.setText(R.string.time_voice)
        senderSoundHolder.senderSoundView.waveformSeekBar.progress = 0f
    }

    private fun Message.setupWaveForm(senderSoundHolder: SenderSoundViewHolder) {
        runBlocking(Dispatchers.IO) {

            senderSoundHolder.senderSoundView.waveformSeekBar.setSampleFrom(soundUri)

        }
    }

    private fun initializeTimer() {
        UtilsReference.myTimer = adapterTimer
    }

    private fun Message.startSound(senderSoundHolder: SenderSoundViewHolder) {
        UtilsReference.customMediaPlayer!!.setupMediaPlayer(soundUri)
        UtilsReference.customMediaPlayer!!.startRunSound()
        UtilsReference.myTimer.start()



        changeIcon(senderSoundHolder)
    }

    private fun resumeSound(senderSoundHolder: SenderSoundViewHolder) {
        UtilsReference.customMediaPlayer!!.resumeSound()
        UtilsReference.myTimer.start()
        changeIcon(senderSoundHolder)
    }

    private fun Message.pauseSound(senderSoundHolder: SenderSoundViewHolder) {
        UtilsReference.myTimer.pause()
        UtilsReference.customMediaPlayer!!.pauseSound()
        changeIcon(senderSoundHolder)
        println("------------uri when is pause = $soundUri")
    }

    private fun changeIcon(senderSoundHolder: SenderSoundViewHolder) {
        if (UtilsReference.isSoundRuning) {
            senderSoundHolder.senderSoundView.btnSend.setBackgroundResource(
                R.drawable.pause_icon
            )
        } else if (UtilsReference.isSoundStop || UtilsReference.isSoundPause) {
            senderSoundHolder.senderSoundView.btnSend.setBackgroundResource(
                R.drawable.play_icon
            )
        }
    }


    override fun getItemCount(): Int {
        return messegeList.value!!.size
    }

    private fun setupMediaPlayerListener(onCompleteListener: () -> Unit,onPause:()->Unit) {
        UtilsReference.customMediaPlayer!!.onCompleteListener(onCompleteListener,onPause)
    }

    override fun onTimerTick(duration: String) {
        timerSenderSoundHolder!!.senderSoundView.tvVoiceTime.text = duration
        setupPercentage()
        timerSenderSoundHolder!!.senderSoundView.waveformSeekBar.progress = percentage.toFloat()
        println("buf = ${UtilsReference.customMediaPlayer!!.mediaPlayer.contentBufferedPosition}")// whole number
       println("----amp = ${  UtilsReference.customMediaPlayer!!.mediaPlayer.contentPosition}")// partNumber amp
    }

    private fun setupPercentage() {
        partNumber = UtilsReference.customMediaPlayer!!.mediaPlayer.contentPosition.toDouble()
        wholeNumber = UtilsReference.customMediaPlayer!!.mediaPlayer.contentBufferedPosition.toDouble()

        if (partNumber != 0.0) {
            percentage = (partNumber / wholeNumber).toDouble() * 100
            println("percentage = $percentage")
        }
    }


}