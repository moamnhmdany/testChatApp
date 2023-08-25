package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import com.example.testchatapp.feature_authetication.domain.model.Users

class ViewModel_SignUp() : ViewModel() {
       val user = ArrayList<Users>()
   val usersLiveData : MutableLiveData<ArrayList<Users> > by lazy {
       MutableLiveData<ArrayList<Users> >()
   }


}