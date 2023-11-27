package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.auth.FirebaseAuth

class MySharedPreferance (context: Context,fileName:String){

      var sharedPref: SharedPreferences =
        context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

     fun saveFirstUserId(){
        sharedPref.edit().apply(){
            putString("id", FirebaseAuth.getInstance().uid)
            apply()
        }
    }

     fun getFirstUserId(): String? {
        return sharedPref.getString("id","default_value")
    }
}