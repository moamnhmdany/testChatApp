package com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.featuer_chat.domain.use_case.UseCaseGetAllUsers
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFriendModel : ViewModel() {
    val singletonClass = UtilsReference

    fun getUnfriendUsersDataBase(){
      //  var getCase = UtilsReference.useCaseGetAllUsers
        viewModelScope.launch(Dispatchers.IO) {
            singletonClass.useCaseGetAllUsers.setupUersUnfriendList()
        }
    }


}
