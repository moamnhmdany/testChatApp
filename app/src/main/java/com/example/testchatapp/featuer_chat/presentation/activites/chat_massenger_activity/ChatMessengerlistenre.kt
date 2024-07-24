package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.LifecycleOwner
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.featuer_chat.presentation.adapters.MessengerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import linc.com.amplituda.Amplituda
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ChatMessengerlistenre {


    fun observeMessageList(
        intent: Intent, lifeCycle: LifecycleOwner, ui: ActivityChatMessangerPageBinding,
        context: Context
    ) {
        sendListener(intent)
        UtilsReference.mutableMessageList.observe(lifeCycle) {
            setupRecycleView(context, ui)
        }
        println("---------------> observeMessageList is done running")
    }

    private fun setupRecycleView(context: Context, ui: ActivityChatMessangerPageBinding) {
        val layoutManager = LinearLayoutManager(context)

        ui.chatMessagesRecycleView.layoutManager = layoutManager
        UtilsReference.messageChatAdapter.messegeList = UtilsReference.mutableMessageList

        ui.chatMessagesRecycleView.adapter = UtilsReference.messageChatAdapter

        val itemCount = (ui.chatMessagesRecycleView.adapter as MessengerAdapter).itemCount
        layoutManager.scrollToPosition(itemCount - 1)
        println("-----------------> setupRecycleView done")
    }

    private fun sendListener(intent: Intent) {


        val userData = getIntentData(intent)
        if (userData is UserChatRoom) {
            UtilsReference.unFriendUser.userUnfriendId = userData.id
            UtilsReference.unFriendUser.userId = FirebaseAuth.getInstance().uid.toString()
            UtilsReference.unFriendUser.userUnfriendUserName = userData.userName
        } else {
            val unFriend = getUserUnfriendData(intent)
            UtilsReference.unFriendUser = unFriend
        }

        setupRoomId(UtilsReference.unFriendUser)
        val listener = setupListener()
        UtilsReference.chatMessageViewModel.getMessages(UtilsReference.roomId, listener)
        println("-----------> sendListener is done running")
    }

    private fun setupListener(): ValueEventListener {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                UtilsReference.mutableMessageList.value?.clear()
                if (snapshot.exists()) {
                    updateMessageId(snapshot)
                    snapshot.children.forEach {

                        println("----------------- the msg id = ${it.key.toString()}")
                        val message = it.getValue(Message::class.java)!!
                        UtilsReference.messagesList.add(message)
                    }

                    UtilsReference.mutableMessageList.postValue(UtilsReference.messagesList)

                    println("----------------------> done sort messages")
                } else {
                    println("--------------> snapshot data not found")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                println("--------------> snapShot is cancelled")
            }


        }
        println("-----------------------------> done run setupListener")
        return listener

    }

    private fun updateMessageId(snapshot: DataSnapshot) {
        UtilsReference.messageId =
            snapshot.children.last().getValue(Message::class.java)!!.messageId
    }

    fun sendMessage(ui: ActivityChatMessangerPageBinding, intent: Intent) {
        setupUserChatData(intent)
        ui.btnSendMsg.setOnClickListener {
            val msg = ui.chatMsgTextBox.text.toString()
            println("------------------the message is = $msg")
            setupRoomId(UtilsReference.unFriendUser)
            setupMessage(msg, UtilsReference.unFriendUser)
            UtilsReference.chatMessageViewModel.sendMsg()
            clearText(ui)
        }
        println("------------------------> sendMessage is done running")
    }
    private fun setupUserChatData(intent: Intent){
        val userData = getIntentData(intent)
        if (userData is UserChatRoom) {
            UtilsReference.unFriendUser.userUnfriendId = userData.id
            UtilsReference.unFriendUser.userId = FirebaseAuth.getInstance().uid.toString()
            UtilsReference.unFriendUser.userUnfriendUserName = userData.userName
        } else {
            val unFriend = getUserUnfriendData(intent)
            UtilsReference.unFriendUser = unFriend
        }
    }
    private fun getIntentData(intent: Intent): Any {
        val check = intent.getIntExtra("checkClass", 0)

        if (check == 1)
            return getUserRoomData(intent)
        else
            return getUserUnfriendData(intent)

    }
    private fun getUserRoomData(intent: Intent): UserChatRoom {

        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            intent.getParcelableExtra("userRoomMate", UserChatRoom::class.java)

        } else {
            intent.getParcelableExtra<UserChatRoom>("userRoomMate")
        }
        val data = userData!!.userName
        println("-----------------" + data)
        println("------------------------> getUserUnfriendData ")
        return userData
    }
    private fun getUserUnfriendData(intent: Intent): UsersUnfriend {

        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            intent.getParcelableExtra("userDataFriend", UsersUnfriend::class.java)

        } else {
            intent.getParcelableExtra<UsersUnfriend>("userDataFriend")
        }
        val data = userData!!.userUnfriendUserName
        println("-----------------" + data)
        println("------------------------> getUserUnfriendData ")
        return userData
    }

    private fun clearText(ui: ActivityChatMessangerPageBinding) {
        ui.chatMsgTextBox.text.clear()
    }

    private fun setupMessage(msg: String, unFriend: UsersUnfriend) {
        UtilsReference.msg.message = msg
        UtilsReference.msg.messageId = getMessageId()
        UtilsReference.msg.userId = unFriend.userId
        UtilsReference.msg.receiverId = unFriend.userUnfriendId
        UtilsReference.msg.messageTime = getTime()
    }

    private fun getMessageId(): String {
        UtilsReference.messageId = (UtilsReference.messageId.toInt() + 1).toString()
        println("-------------message id is = ${UtilsReference.messageId}")
        return UtilsReference.messageId
    }

    private fun setupRoomId(unFriend: UsersUnfriend) {
        UtilsReference.roomId = sort(unFriend.userId, unFriend.userUnfriendId)
        println("----------------> done running setupRoomId ${UtilsReference.roomId}")
    }

    private fun getTime(): String =
        SimpleDateFormat("hh:mm:ss a", Locale("ar")).format(Date())






    private fun sort(senderId: String, receiverId: String): String {
        if (senderId.compareTo(receiverId) <= 0) {
            println("-------------> done run sort")
            return senderId + receiverId
        } else {
            println("-------------> done run sort ")
            return receiverId + senderId
        }
    }


    fun goToMain(context: Context, ui: ActivityChatMessangerPageBinding) {
        ui.btnBackChatMsg.setOnClickListener {
            val intent = Intent(context, UsersChatRoomListActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun openSoundRecord(ui: ActivityChatMessangerPageBinding) {

        ui.lyBottomBar.visibility = View.GONE
        ui.voiceLy.getRoot().visibility = View.VISIBLE

    }

    private fun closeSoundRecord(ui: ActivityChatMessangerPageBinding) {

        ui.voiceLy.getRoot().visibility = View.GONE
        ui.lyBottomBar.visibility = View.VISIBLE

    }


    fun keyBoardListener(activity: ChatMessengerActivity, ui: ActivityChatMessangerPageBinding) {
        val lp = ui.chatMessagesRecycleView.layoutParams
        KeyboardVisibilityEvent.setEventListener(activity, object :
            KeyboardVisibilityEventListener {

            override fun onVisibilityChanged(isOpen: Boolean) {
                if (isOpen) {
                    if (ui.chatMessagesRecycleView.adapter != null) {
                        val itemCount =
                            (ui.chatMessagesRecycleView.adapter as MessengerAdapter).itemCount
                        ui.chatMessagesRecycleView.layoutManager?.scrollToPosition(itemCount - 1)
                        println("-------------------keyboard is open--------------------")
                        lp.height = 700
                        ui.chatMessagesRecycleView.layoutParams = lp
                    }
                } else {
                    val lp2 = ui.chatMessagesRecycleView.layoutParams
                    lp2.height = 1600
                    ui.chatMessagesRecycleView.layoutParams = lp2
                }
            }
        })
    }

    fun initializeRecorder(context: Context): RecordVoiceHandler {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            return RecordVoiceHandler(MediaRecorder(context))

        } else {
            return RecordVoiceHandler(MediaRecorder())
        }
    }


    @RequiresApi(Build.VERSION_CODES.Q)
    fun recordListener(
        recorder: RecordVoiceHandler,
        activity: ChatMessengerActivity,
        ui: ActivityChatMessangerPageBinding,
        timer: CustomsTimer,
        permissionsHandler: PermissionsHandler
    ) {
        permissionsHandler.requestPermissions()
        ui.btnMic.setOnClickListener {

            if (permissionsHandler.checkPermission()) {
                ui.voiceLy.myWaveForm.clear()
                openSoundRecord(ui)
                recorder.setupRecordVoice(activity)
                recorder.startRecord()
                ui.voiceLy.btnPlayVoice.setBackgroundResource(R.drawable.pause_icon)
                timer.start()
            }
        }

        ui.voiceLy.btnPlayVoice.setOnClickListener {
            when {
                UtilsReference.isPaused -> {
                    recorder.resumeRecorder()
                    ui.voiceLy.btnPlayVoice.setBackgroundResource(R.drawable.pause_icon)
                    timer.start()
                }

                UtilsReference.isRecorded -> {
                    recorder.pauseRecorder()
                    timer.pause()
                    ui.voiceLy.btnPlayVoice.setBackgroundResource(R.drawable.mic_icon)
                }
            }
        }

        ui.voiceLy.btnDeleteVoice.setOnClickListener {
            recorder.removeRecorder()
            timer.stop()
            closeSoundRecord(ui)
            UtilsReference.audioPath = ""
        }
    }

    fun sendSoundMessage(ui: ActivityChatMessangerPageBinding,
                         intent: Intent,recorder: RecordVoiceHandler,timer: CustomsTimer) {

        ui.voiceLy.btnSendVoice.setOnClickListener {
            stopRecord(recorder, timer, ui)
            setupUserChatData(intent)
            setupRoomId(UtilsReference.unFriendUser)
            setupMessage("soundMessage", UtilsReference.unFriendUser)

            UtilsReference.chatMessageViewModel.uploadSound() {


                UtilsReference.chatMessageViewModel.sendMsg()
                UtilsReference.audioPath = ""
            }
        }
    }

    private fun stopRecord(recorder: RecordVoiceHandler, timer: CustomsTimer, ui: ActivityChatMessangerPageBinding){
        recorder.stopRecord()
        timer.stop()
        closeSoundRecord(ui)

    }

    fun inililizeMediaPlayer(context: Context){
        UtilsReference.mediaPlayer = ExoPlayer.Builder(context).build()
        UtilsReference.myAmplituda = Amplituda(context)
        UtilsReference.customMediaPlayer = MyMediaPlayer(UtilsReference.mediaPlayer!!)
    }

}
