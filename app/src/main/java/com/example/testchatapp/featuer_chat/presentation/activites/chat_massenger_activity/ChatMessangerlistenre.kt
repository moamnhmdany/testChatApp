package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date
import java.util.Locale
import java.util.UUID

class ChatMessangerlistenre {

    fun observeMessageList(
        intent: Intent, lifeCycle: LifecycleOwner, ui: ActivityChatMessangerPageBinding,
        context: Context
    ) {
        sendListener(intent)
        UtilsReference.mutableMessageList.observe(lifeCycle, Observer {
            setupRecycleView(context, ui)
        })
        println("---------------> observeMessageList is done running")
    }

    private fun setupRecycleView(context: Context, ui: ActivityChatMessangerPageBinding) {
        val layoutManager = LinearLayoutManager(context)
        ui.chatMessagesRecycleView.layoutManager = layoutManager
        UtilsReference.messageChatAdapter.messegeList = UtilsReference.mutableMessageList
        ui.chatMessagesRecycleView.adapter = UtilsReference.messageChatAdapter
        println("-----------------> setupRecycleView done")
    }

    private fun sendListener(intent: Intent) {
        val unFriend = getUserUnfriendData(intent)
        setupRoomId(unFriend)
        val listener = setupListener()
        UtilsReference.chatMessageViewModel.getMessages(UtilsReference.roomId, listener)
        println("-----------> sendListener is done running")
    }

    private fun setupListener(): ValueEventListener {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    UtilsReference.mutableMessageList.value?.clear()

                    snapshot.children.forEach {

                        println("----------------- the msg id = ${it.key.toString()}")
                        val message = it.getValue(Message::class.java)!!
                        UtilsReference.messagesList.add(message)
                    }
                    UtilsReference.mutableMessageList.postValue(UtilsReference.messagesList)
                    UtilsReference.mutableMessageList.value?.sortBy{
                        it.messageTime
                    }
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

    fun sendMessage(ui: ActivityChatMessangerPageBinding, intent: Intent) {
        val unFriend = getUserUnfriendData(intent)

        ui.btnSendMsg.setOnClickListener {
            val msg = ui.chatMsgTextBox.text.toString()
            println("------------------the message is = $msg")
            setupRoomId(unFriend)
            setupMessage(msg, unFriend)
            UtilsReference.chatMessageViewModel.sendMsg()
            clearText(ui)
        }
    }

    private fun clearText(ui: ActivityChatMessangerPageBinding) {
        ui.chatMsgTextBox.text.clear()
    }

    private fun setupMessage(msg: String, unFriend: UsersUnfriend) {
        //val time =  Calendar.getInstance().time.toString()
        UtilsReference.msg.message = msg
        UtilsReference.msg.messageId = UUID.randomUUID().toString()
        UtilsReference.msg.userId = unFriend.userId
        UtilsReference.msg.receiverId = unFriend.userUnfriendId
        UtilsReference.msg.messageTime = getTime()

    }

    private fun setupRoomId(unFriend: UsersUnfriend) {
        UtilsReference.roomId = sort(unFriend.userId, unFriend.userUnfriendId)
        println("----------------> done running setupRoomId ${UtilsReference.roomId}")
    }

    private fun getTime(): String =
        SimpleDateFormat("hh:mm:ss a", Locale("ar")).format(Date())

    private fun getUserUnfriendData(intent: Intent): UsersUnfriend {
        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("userDataFriend", UsersUnfriend::class.java)

        } else {
            intent.getParcelableExtra<UsersUnfriend>("userDataFriend")
        }
        val data = userData!!.userUnfriendUserName
        println("-----------------" + data)
        println("------------------------> getUserUnfriendData ")
        return userData
    }

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

}