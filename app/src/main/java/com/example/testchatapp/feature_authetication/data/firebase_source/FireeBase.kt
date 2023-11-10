package com.example.testchatapp.feature_authetication.data.firebase_source

import android.content.Context
import android.widget.Toast
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class FireeBase : MyDataBaseDao {


    private lateinit var userDataBase: FirebaseAuth
    private lateinit var myRealTimeDataBase: FirebaseDatabase
    private lateinit var myTaskResult: Task<AuthResult>
    var isFirstUserExists = false
    val usersRef = Firebase.database.reference.child("Users")
    val usersUnfriendRef = Firebase.database.reference.child("UsersUnfriend")



    override suspend fun loginWithEmailFireBase(user: Users, context: Context): Task<AuthResult> {
               this.userDataBase = Firebase.auth

        myTaskResult = userDataBase.signInWithEmailAndPassword(user.email, user.password)

        println("my state is ============================> $myTaskResult")
        return myTaskResult
    }

    override suspend fun createUserWithEmailAndPassword(user: Users, context: Context) {
        if (userValid(user)) {
            this.userDataBase = Firebase.auth
            println("================> the email is ")
            println(user.email)

            myTaskResult =
                this.userDataBase.createUserWithEmailAndPassword(user.email, user.password)

             addUserFromResult( user)


        } else {
            makeToast(context, "please write your information correctly")
        }


    }


    private  fun addUserFromResult(user: Users) {

        try {
            myTaskResult.addOnCompleteListener { task ->

                val id = task.result.user!!.uid

                if (task.isComplete) {
                    if (task.isSuccessful) {


                               isFirstuserExist(onSuccessListener = {
                                   checkAndRun(user,id)

                                 }
                               )







                    }
                } else
                    println("failed add new user")
            }
        } catch (e: FirebaseNetworkException) {
            println("network error with = $e")
        }

    }

    private fun checkAndRun(user: Users,id:String) {
        if (FireBaseListeners.isFirstUserExists) {
            makeUserRecord(user, id)
            println("done add data base of user ")
            addUnfriendListToFireBse()
            unFriendNewUserAddToFirstUser()
        }else{
            makeUserRecord(user, id)
        }

    }

    private  fun isFirstuserExist(onSuccessListener:()-> Unit) {
         usersRef.addValueEventListener(FireBaseListeners.isFirstUserExistsListener{onSuccessListener()})
         println("========================> done check first user")

    }


    private fun makeUserRecord(user: Users, id: String) {

        try {
            user.id = id
            usersRef.child(id).setValue(user)
            println("========================> done add new user to realtime data base ")
        } catch (e: Exception) {
            println("database error = $e")
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun addUnfriendListToFireBse()=

        GlobalScope.launch(Dispatchers.IO) {
            makeUnfriendList()
            usersUnfriendRef.child(UtilsReference.user.id)
                .setValue(UtilsReference.listUsersUnfriends).await()
        }


    @OptIn(DelicateCoroutinesApi::class)
    private fun makeUnfriendList() {
        myRealTimeDataBase = Firebase.database
        GlobalScope.launch(Dispatchers.IO) {
            usersRef.get().await()
            usersRef.addListenerForSingleValueEvent(FireBaseListeners.unFriendListListener())
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun unFriendNewUserAddToFirstUser() {
        myRealTimeDataBase = Firebase.database

        GlobalScope.launch(Dispatchers.IO) {

            val userId = setupUnfriend()

            usersUnfriendRef.child(userId).setValue(UtilsReference.unFriendUser)
        }
    }

    private suspend fun setupUnfriend(): String {
        val userId = getFirstUser()!!.id
        val userUnfriendId = UtilsReference.user.id
        val userUnfriendUsername = UtilsReference.user.userName

        UtilsReference.unFriendUser.userId = userId
        UtilsReference.unFriendUser.userUnfriendId = userUnfriendId
        UtilsReference.unFriendUser.userUnfriendUserName = userUnfriendUsername
        return userId
    }

    private suspend fun getFirstUser(): Users? {
        return usersRef.snapshots.first().getValue(Users::class.java)
    }

    private fun makeToast(context: Context, msg: String) {
        println("user data is empty")
        Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            .show()
    }

    fun check(id: String): Boolean {
        return id == FirebaseAuth.getInstance().uid
    }

    private fun userValid(user: Users): Boolean {

        return (user.userName.isNotEmpty() && user.email.isNotEmpty()
                && user.password.isNotEmpty())

    }
}