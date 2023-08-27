package com.example.testchatapp.feature_authetication.domain.use_case

import com.example.testchatapp.feature_authetication.domain.model.Users

class UserOpareations() {
     companion object{
         fun newUser():Users{
             val user = Users()
             return  user
         }
     }

}