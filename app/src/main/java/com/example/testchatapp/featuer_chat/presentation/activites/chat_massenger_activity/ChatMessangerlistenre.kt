package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val itemCount = (ui.chatMessagesRecycleView.adapter as MessengerAdapter).itemCount
        layoutManager.scrollToPosition(itemCount-1)
        println("-----------------> setupRecycleView done")
    }

    private fun sendListener(intent: Intent) {


         val userData = getIntentData(intent)
        if (userData is UserChatRoom) {
            UtilsReference.unFriendUser.userUnfriendId = userData.id
            UtilsReference.unFriendUser.userId = FirebaseAuth.getInstance().uid.toString()
            UtilsReference.unFriendUser.userUnfriendUserName = userData.userName
        }else{
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
    private fun updateMessageId(snapshot: DataSnapshot){
        UtilsReference.messageId =
            snapshot.children.last().getValue(Message::class.java)!!.messageId
    }

    fun sendMessage(ui: ActivityChatMessangerPageBinding, intent: Intent) {
        val userData = getIntentData(intent)
        if (userData is UserChatRoom) {
            UtilsReference.unFriendUser.userUnfriendId = userData.id
            UtilsReference.unFriendUser.userId = FirebaseAuth.getInstance().uid.toString()
            UtilsReference.unFriendUser.userUnfriendUserName = userData.userName
        }else{
            val unFriend = getUserUnfriendData(intent)
            UtilsReference.unFriendUser = unFriend
        }
        val unFriend = UtilsReference.unFriendUser

        ui.btnSendMsg.setOnClickListener {
            val msg = ui.chatMsgTextBox.text.toString()
            println("------------------the message is = $msg")
            setupRoomId(unFriend)
            setupMessage(msg, unFriend)
            UtilsReference.chatMessageViewModel.sendMsg()
            clearText(ui)
        }
        println("------------------------> sendMessage is done running")
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

    private fun getMessageId():String{
        UtilsReference.messageId =  (UtilsReference.messageId.toInt() + 1).toString()
        println("-------------message id is = ${UtilsReference.messageId}")
        return UtilsReference.messageId
    }
    private fun setupRoomId(unFriend: UsersUnfriend) {
        UtilsReference.roomId = sort(unFriend.userId, unFriend.userUnfriendId)
        println("----------------> done running setupRoomId ${UtilsReference.roomId}")
    }

    private fun getTime(): String =
        SimpleDateFormat("hh:mm:ss a", Locale("ar")).format(Date())

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

    private fun getIntentData(intent: Intent):Any{
        val check = intent.getIntExtra("checkClass",0)

       if(check == 1)
           return getUserRoomData(intent)
        else
        return getUserUnfriendData(intent)

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