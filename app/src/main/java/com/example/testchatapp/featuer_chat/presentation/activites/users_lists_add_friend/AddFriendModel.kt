package com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.databinding.ActivityUsersListAddBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UseCaseGetAllUsers
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.adapters.AddFriendAdapter
import com.example.testchatapp.feature_authetication.domain.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFriendModel : ViewModel() {
      private val userList = UtilsReference.mutableUsersList
      private val getCase = UseCaseGetAllUsers()
       fun update( ui: ActivityUsersListAddBinding){
           UseCaseGetAllUsers.updateAddFriend(ui)
      }
     fun getUsers(users:ArrayList<Users>){
        viewModelScope.launch(Dispatchers.IO) {
            getCase.getAllUsers(users)
        }
        userList.value = users
    }
}