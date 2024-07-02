package com.example.testchatapp.featuer_chat.domain.use_case

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.models.UserChats
import com.example.testchatapp.featuer_chat.domain.models.UserFriends
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.ChatMessageViewModel
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UserRoomIntent
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.ViewModelUserRoomList
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.AddFriendModel
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.UsersListAddActivity
import com.example.testchatapp.featuer_chat.presentation.adapters.AddFriendAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.MessengerAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.UserRoomChatListAdapter
import com.example.testchatapp.feature_authetication.domain.model.Users

object UtilsReference {
     var user = Users()
     var userMutableliveData = MutableLiveData<Users>()
     var list = ArrayList<Users>()


    var listUsersUnfriends = ArrayList<UsersUnfriend>()

    var  chatRoomListAdapter =  UserRoomChatListAdapter()
    var roomList = ArrayList<UserChatRoom>()
    var userRoomList = MutableLiveData<ArrayList<UserChatRoom>>()
    var viewModelRoomsList = ViewModelUserRoomList()
    var addFriendListAdapter =  AddFriendAdapter()

    lateinit var interfaceChat : UsersListAddActivity
    lateinit var interfaceChatRoom : UserRoomIntent

    var messageChatAdapter =  MessengerAdapter()

    var   mutableUsersList = MutableLiveData<ArrayList<Users>>()

    var   mutableUsersUnFriendsList = MutableLiveData<ArrayList<UsersUnfriend>>()

    var unFriendUser = UsersUnfriend()


    var   chatsFriends = MutableLiveData<ArrayList<UserChats>>()
    var   chatFriend = UserChats()
    var addFriendModel = AddFriendModel()

    var useCaseGetAllUsers = UseCaseGetAllUsers()
    var userFriends = UsersFriendCase()

    var msg = Message()
    var messageId = "0"
    var roomId = ""
    var sendCase = SendMessageCase()
    var chatMessageViewModel = ChatMessageViewModel()
    var mutableMessageList = MutableLiveData<ArrayList<Message>>()
    var messagesList = ArrayList<Message>()
    var getMessagesCase = UserMessageCase()
    var userFriendList = ArrayList<UserFriends>()
    var mutableUserFriendsList = MutableLiveData<ArrayList<UserFriends>>()
     var imageUri : Uri? = null

    var permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO)
    const val REQUEST_CODE = 200
    var permissonGranted = false

}