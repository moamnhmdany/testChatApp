package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.databinding.ActivityChatMessangerPageBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class ChatMessengerActivity : AppCompatActivity(),CustomsTimer.OnTimerTickListener {
    private lateinit var ui : ActivityChatMessangerPageBinding
    private lateinit var record : RecordVoiceHandler
    private val action = ChatMessengerlistenre()
    private val setting = Utiles()
    private val  permissionsHandler = PermissionsHandler(this)
    private val timer = CustomsTimer(this)


    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         ui =  setting.settingChatMessengerPage(this, this)
         record = action.initializeRecorder(this)
          UtilsReference.myTimer = timer
          action.inililizeMediaPlayer(this)

        action.goToMain(this, ui)
        action.sendMessage(ui, intent)
        action.observeMessageList(intent, this, ui, this)
        action.keyBoardListener(this, ui)
        action.recordListener(record, this, ui, timer,permissionsHandler)
        action.sendSoundMessage(ui, intent, record, timer)


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    UtilsReference.permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
    }

    override fun onTimerTick(duration: String) {
        ui.voiceLy.tvVoiceTime.text = duration
        ui.chatMessagesRecycleView.adapter
        ui.voiceLy.myWaveForm.addAmplitude(record.recorder.maxAmplitude.toFloat())
    }

}
