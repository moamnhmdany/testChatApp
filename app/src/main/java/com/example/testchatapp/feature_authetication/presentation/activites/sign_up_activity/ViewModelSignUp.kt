package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.content.Context
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.domain.use_case.UseCaseRegisterUser
import kotlinx.coroutines.launch

class ViewModelSignUp : ViewModel() {

       private val usersList = ArrayList<Users>()
       private val usersLiveData =  MutableLiveData<ArrayList<Users> >()
      fun createUser(user:Users,context: Context){
          usersList.add(user)

          usersLiveData.value= usersList
         viewModelScope.launch {

             UseCaseRegisterUser().createUser(user,context)
         }


      }
}