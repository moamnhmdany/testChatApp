package com.example.testchatapp.featuer_chat.domain.use_case

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.domain.models.Message
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.models.UserChats
import com.example.testchatapp.featuer_chat.domain.models.UserFriends
import com.example.testchatapp.featuer_chat.domain.models.UsersUnfriend
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.ChatMessageViewModel
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.ViewModelUserRoomList
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.AddFriendModel
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.UsersListAddActivity
import com.example.testchatapp.featuer_chat.presentation.adapters.AddFriendAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.MessengerAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.UserRoomChatListAdapter
import com.example.testchatapp.feature_authetication.domain.model.Users

object UtilsReference {
     var user = Users()
     var list = ArrayList<Users>()


    var listUsersUnfriends = ArrayList<UsersUnfriend>()
    var listUserFriends = ArrayList<UserFriends>()

    var  chatRoomListAdapter =  UserRoomChatListAdapter()
    var roomList = ArrayList<UserChatRoom>()
    var userRoomList = MutableLiveData<ArrayList<UserChatRoom>>()
    var viewModelRoomsList = ViewModelUserRoomList()
    var addFriendListAdapter =  AddFriendAdapter()

    lateinit var interfaceChat : UsersListAddActivity

    var messageChatAdapter =  MessengerAdapter()

    var   mutableUsersList = MutableLiveData<ArrayList<Users>>()

    var   mutableUsersUnFriendsList = MutableLiveData<ArrayList<UsersUnfriend>>()
    var   mutableUserFriendsList = MutableLiveData<ArrayList<UserFriends>>()

    var unFriendUser = UsersUnfriend()
    var userFriend = UserFriends()

    var   mutableMessagesList = MutableLiveData<ArrayList<Message>>()

    var   chatsFriends = MutableLiveData<ArrayList<UserChats>>()
    var   chatFriend = UserChats()
    var addFriendModel = AddFriendModel()

    var useCaseGetAllUsers = UseCaseGetAllUsers()
    var userFriends = UsersFriendCase()

    var msg = Message()
    var roomId = ""
    var sendCase = SendMessageCase()
    var chatMessageViewModel = ChatMessageViewModel()



}