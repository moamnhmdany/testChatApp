package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.featuer_chat.data.repository.RepositoryImpl
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.use_case.UploadSoundCase
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatMessageViewModel() : ViewModel() {

    lateinit var messagesList: MutableLiveData<ArrayList<Message>>
    private val repo = RepositoryImpl()
    private val soundCase = UploadSoundCase(repo)

    fun sendMsg() {
        viewModelScope.launch(Dispatchers.IO) {
            UtilsReference.sendCase.send(
                UtilsReference.roomId, UtilsReference.msg.messageId, UtilsReference.msg
            )
        }
    }

     fun getMessages(roomId: String, listener: ValueEventListener){

       viewModelScope.launch (Dispatchers.IO){
           UtilsReference.getMessagesCase.getMessages(roomId, listener)
       }

         println("---------------------> done run getMessages in view model")
    }

    fun uploadSound(sendMsg: ()->Unit){
        viewModelScope.launch(Dispatchers.Main) {
            soundCase.uploadSound(sendMsg)
        }
    }

}
