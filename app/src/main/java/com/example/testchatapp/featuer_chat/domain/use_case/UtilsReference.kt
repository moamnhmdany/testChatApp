package com.example.testchatapp.featuer_chat.domain.use_case

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UserChats
import com.example.testchatapp.featuer_chat.domain.models.UserFriends
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.presentation.adapters.AddFriendAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.MessengerAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.UserItemAdapter
import com.example.testchatapp.feature_authetication.domain.model.Users

object UtilsReference {

     var user = Users()
     var list = ArrayList<Users>()

    var listUsersUnfriends = ArrayList<UsersUnfriend>()
    var listUserFriends = ArrayList<UserFriends>()

    var  chatListAdapter =  UserItemAdapter()
    var addFriendListAdapter =  AddFriendAdapter()
    var messageChatAdapter =  MessengerAdapter()

    var   mutableUsersList = MutableLiveData<ArrayList<Users>>()

    var   mutableUsersUnFriendsList = MutableLiveData<ArrayList<UsersUnfriend>>()
    var   mutableUserFriendsList = MutableLiveData<ArrayList<UserFriends>>()

    var unFriendUser = UsersUnfriend()
    var userFriend = UserFriends()

    var   mutableMessagesList = MutableLiveData<ArrayList<Message>>()

    var   chatsFriends = MutableLiveData<ArrayList<UserChats>>()
    var   chatFriend = UserChats()




}