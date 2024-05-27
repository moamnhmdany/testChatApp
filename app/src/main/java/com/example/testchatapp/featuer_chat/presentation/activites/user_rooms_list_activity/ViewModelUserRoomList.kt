package com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.UseCaseGetAllUsers
import com.example.testchatapp.featuer_chat.domain.use_case.UsersFriendCase
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelUserRoomList : ViewModel() {
    private val getCase = UseCaseGetAllUsers()
    private val userRoomsListCase = UsersFriendCase()

     fun setRoomList() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = userRoomsListCase.getUserFriendsList()

            data.forEach {
                 val myData = it.getValue(UsersUnfriend::class.java)
                      val userRoom = UserChatRoom()
                        userRoom.id = myData!!.userUnfriendId
                        userRoom.userName= myData.userUnfriendUserName
                        UtilsReference.roomList.add(userRoom)
                println("------------->>>>>----${myData.userUnfriendUserName}")

            }
            UtilsReference.userRoomList.postValue( UtilsReference.roomList)
        }


    }
}