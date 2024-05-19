package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

class ChatMessageViewModel() : ViewModel() {

    lateinit var msgLiveData: MutableLiveData<Message>

    fun sendMsg() {
        viewModelScope.launch(Dispatchers.IO) {
            UtilsReference.sendCase.send(
                UtilsReference.roomId, UUID.randomUUID().toString(), UtilsReference.msg
            )
        }

    }

}