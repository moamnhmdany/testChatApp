package com.example.testchatapp.feature_authetication.data.firebase_source

import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth

interface FireBaseDao {


      fun createUserWithEmailAndPassword(users: Users)
}