package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.domain.use_case.UseCase_RegisterUser
import kotlinx.coroutines.launch

class ViewModel_SignUp() : ViewModel() {

       val usersList = ArrayList<Users>()
   val usersLiveData =  MutableLiveData<ArrayList<Users> >()

      fun createUser(user:Users){
          usersList.add(user)

          usersLiveData.value= usersList
         viewModelScope.launch {
             UseCase_RegisterUser().createUser(user)
         }


      }
}