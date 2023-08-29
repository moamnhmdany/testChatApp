package com.example.testchatapp.feature_authetication.data.firebase_source

import android.content.Context
import android.widget.Toast
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.presentation.activites.login_activity.LoginActionsListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FireeBase : MyDataBaseDao {


    private lateinit var userDataBase: FirebaseAuth
    private lateinit var myRealTimeDataBase: FirebaseDatabase
    private lateinit var myTaskResult: Task<AuthResult>

    override suspend fun createUserWithEmailAndPassword(user: Users, context: Context) {
        if (userValid(user)) {
            this.userDataBase = Firebase.auth
            println("================> the email is ")
            println(user.email)
            myTaskResult =
                this.userDataBase.createUserWithEmailAndPassword(user.email, user.password)
            userDataBase.isSignInWithEmailLink("")
            checkResult(myTaskResult, user)


        } else {
            makeToast(context, "please write your information correctly")
        }


    }

    override suspend fun loginWithEmailFireBase(user: Users, context: Context): Task<AuthResult> {
        this.userDataBase = Firebase.auth

         myTaskResult = userDataBase.signInWithEmailAndPassword(user.email, user.password)

        println("my state is ============================> $myTaskResult")
        return myTaskResult
    }



    private fun makeToast(context: Context, msg: String) {
        println("user data is empty")
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            .show()
    }

    private fun checkResult(taskResultDB: Task<AuthResult>, user: Users) {
        try {
            taskResultDB.addOnCompleteListener { task ->
                val id = task.result.user!!.uid

                if (task.isComplete) {
                    if (task.isSuccessful) {
                        println("done add new user >>>>>>>>>>>>>>>>>>>>")
                        makeUserRecord(user, id)
                        println("done add data base of user ")
                    }

                } else
                    println("failed add new user")
            }
        } catch (e: FirebaseNetworkException) {
            println("network error with = $e")
        }

    }

    private fun userValid(user: Users): Boolean {

        return (user.userName.isNotEmpty() && user.email.isNotEmpty()
                && user.password.isNotEmpty())

    }

    private fun makeUserRecord(user: Users, id: String): FirebaseDatabase {
        myRealTimeDataBase = Firebase.database

        try {
            val dbRef = myRealTimeDataBase.getReference("Users")
            user.id = id
            dbRef.child(id).setValue(user)
        } catch (e: Exception) {
            println("database error = $e")
        }


        return myRealTimeDataBase
    }


}