package com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UseCaseGetAllUsers
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelUsersList :ViewModel(){
    private val getCase =  UseCaseGetAllUsers()
     fun update(ui:ActivityUsersChatListBinding){
         UseCaseGetAllUsers.updateUserList(ui)
     }

     fun getUsers(){

        viewModelScope.launch (Dispatchers.IO){
           getCase.getAllUsers()
        }


     }
}