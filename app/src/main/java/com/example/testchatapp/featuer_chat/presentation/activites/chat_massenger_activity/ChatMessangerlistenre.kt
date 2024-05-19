package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class ChatMessangerlistenre {


    fun goToMain(context: Context, ui: ActivityChatMessangerPageBinding) {
        ui.btnBackChatMsg.setOnClickListener {
            val intent = Intent(context, UsersChatRoomListActivity::class.java)
            context.startActivity(intent)
        }
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
        UtilsReference.msg.reciverId = unFriend.userUnfriendId
        UtilsReference.msg.messageTime = getTime()
    }

    private fun setupRoomId(unFriend:UsersUnfriend){
        UtilsReference.roomId = sort(unFriend.userId, unFriend.userUnfriendId)
    }
    private fun getTime(): String =
        SimpleDateFormat("hh:mm:ss a", Locale.getDefault()).format(Date())

    private fun getUserUnfriendData(intent: Intent): UsersUnfriend {
        val userData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("userDataFriend", UsersUnfriend::class.java)

        } else {
            intent.getParcelableExtra<UsersUnfriend>("userDataFriend")
        }
        val data = userData!!.userUnfriendUserName
        println("-----------------" + data)

        return userData
    }

    fun sort(senderId: String, ReciverId: String): String {

        return if (senderId.compareTo(ReciverId) <= 0) {
            "$senderId-$ReciverId"
        } else {
            "$ReciverId-$senderId"
        }
    }


}