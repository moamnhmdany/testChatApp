package com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.domain.use_case.GetUserDataCase
import com.example.testchatapp.featuer_chat.domain.use_case.UseCaseGetAllUsers
import com.example.testchatapp.featuer_chat.domain.use_case.UsersFriendCase
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelUserRoomList : ViewModel() {
    private val getCase = UseCaseGetAllUsers()
    private val userRoomsListCase = UsersFriendCase()
    private  val userDataCase = GetUserDataCase()
     fun setRoomList() {
        viewModelScope.launch(Dispatchers.IO) {

            userRoomsListCase.getUserFriendsList(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    UtilsReference.roomList.clear()
                    snapshot.children.forEach {
                        val myData = it.getValue(UsersUnfriend::class.java)
                        val userRoom = UserChatRoom()
                        userRoom.id = myData!!.userUnfriendId
                        userRoom.userName= myData.userUnfriendUserName
                        UtilsReference.roomList.add(userRoom)
                        println("------------->>>>>----${myData.userUnfriendUserName}")

                    }
                    UtilsReference.userRoomList.postValue( UtilsReference.roomList)
                }

                override fun onCancelled(error: DatabaseError) {
                    println("----------------- snap shot is cancelled")
                }
            })
        }

    }


    fun getUserData(){
         userDataCase.getUserData()

    }
}