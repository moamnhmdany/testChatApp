package com.example.testchatapp.featuer_chat.domain.use_case

import androidx.lifecycle.MutableLiveData
import com.example.testchatapp.featuer_chat.presentation.adapters.AddFriendAdapter
import com.example.testchatapp.featuer_chat.presentation.adapters.UserItemAdapter
import com.example.testchatapp.feature_authetication.domain.model.Users

object UtilsReference {


    var list = ArrayList<Users>()
    var  chatListAdapter =  UserItemAdapter()
    var addFriendListAdapter =  AddFriendAdapter()
    val   mutableUsersList = MutableLiveData<ArrayList<Users>>()


}