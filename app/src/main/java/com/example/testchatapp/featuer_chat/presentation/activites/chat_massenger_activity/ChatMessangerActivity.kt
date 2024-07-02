package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.adapters.MessengerAdapter
import com.example.testchatapp.feature_authetication.presentation.util.Utiles
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

class ChatMessangerActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityChatMessangerPageBinding
    private val action = ChatMessangerlistenre()
    private val setting = Utiles()
    private val permissionsHandler = PermissionsHandler(this)
    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui =  setting.settingChatMessangerPage(this, this)
        action.goToMain(this, ui)
        action.sendMessage(ui, intent)
        action.observeMessageList(intent, this, ui, this)
//        permissionsHandler.checkPermission()
//        permissionsHandler.requestPermissions()

        val lp = ui.chatMessagesRecycleView.layoutParams
        KeyboardVisibilityEvent.setEventListener(this, object : KeyboardVisibilityEventListener {

            override fun onVisibilityChanged(isOpen: Boolean) {
                if (isOpen) {
                    if (ui.chatMessagesRecycleView.adapter != null) {
                       val  itemCount =
                            (ui.chatMessagesRecycleView.adapter as MessengerAdapter).itemCount
                        ui.chatMessagesRecycleView.layoutManager?.scrollToPosition(itemCount - 1)
                        println("-------------------keyboard is open--------------------")
                        lp.height = 700
                        ui.chatMessagesRecycleView.layoutParams = lp
                    }
                } else {
                    val lp = ui.chatMessagesRecycleView.layoutParams
                    lp.height = 1600
                    ui.chatMessagesRecycleView.layoutParams = lp
                }
            }
        })

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    UtilsReference.permissonGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
    }
}
