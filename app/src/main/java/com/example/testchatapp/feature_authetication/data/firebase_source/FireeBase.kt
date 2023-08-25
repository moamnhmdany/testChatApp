package com.example.testchatapp.feature_authetication.data.firebase_source
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
class FireeBase():FireBaseDao {


    private  lateinit var auth:FirebaseAuth

    override fun createUserWithEmailAndPassword() {
        auth = Firebase.auth
    }

}