package com.example.testchatapp.feature_authetication.data.firebase_source

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FireeBase(val context: Context) : MyDataBaseDao {


    private lateinit var userDataBase: FirebaseAuth
    private lateinit var myRealTimeDataBase: FirebaseDatabase
    private lateinit var myTaskResult: Task<AuthResult>
    private val usersRef = Firebase.database.reference.child("Users")
    private val usersUnfriendRef = Firebase.database.reference.child("UsersUnfriend")
    lateinit var userRefStorage : StorageReference
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
            addUserFromResult(user)


        } else {
            makeToast(context, "please write your information correctly")
        }


    }


    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun addUserFromResult(user: Users) {

        try {
            myTaskResult.addOnCompleteListener { task ->

                UtilsReference.user.id = task.result.user!!.uid

                if (task.isComplete) {
                    if (task.isSuccessful) {


                        GlobalScope.launch(Dispatchers.IO) {

                            isFirstUserExist()

                            checkAndRun(user)

                        }
                    }


                } else
                    println("failed add new user")
            }
        } catch (e: FirebaseNetworkException) {
            println("network error with = $e")
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun checkAndRun(user: Users) {
        println("========= start checkAndRun")

        if (FireBaseListeners.isFirstUserExists!= null) {

            makeUserRecord(user)
            println("done add data base of user ")
//            makeUnfriendList()
//            addUnfriendListToFireBse()
//            unFriendNewUserAddToFirstUser()
        } else {
            makeUserRecord(user)
            saveFirstUserId()
            println("done add data base of user ")
        }

        println("=========== finish checkAndRun")
    }

    private suspend fun isFirstUserExist() {
       try {

           FireBaseListeners.isFirstUserExists = usersRef.get().await().children.first()
           println("========================> done check first user ${FireBaseListeners.isFirstUserExists}")

       }catch (e:Exception){
          println(e.toString())
       }
    }


    private suspend fun makeUserRecord(user: Users) {

        try {

            usersRef.child(UtilsReference.user.id).setValue(user).await()
            println("========================> done add new user to realtime data base ")
        } catch (e: Exception) {
            println("database error = $e")
        }
    }

    private suspend fun makeUnfriendList() {
        withContext(Dispatchers.Main) {
            usersRef.get().await().children.forEach { FireBaseListeners.unFriendListListener(it) }
        }
    }

    private suspend fun addUnfriendListToFireBse() {
        withContext(Dispatchers.Main) {
            UtilsReference.mutableUsersUnFriendsList.value?.forEach {
                usersUnfriendRef.child(UtilsReference.user.id)
                    .setValue(it).addOnCompleteListener {
                        println("================== finish addUnfriendListToFireBse")
                    }

            }
        }
    }

    private suspend fun unFriendNewUserAddToFirstUser() {

        val userId = setupUnfriend()
        usersUnfriendRef.child(userId).setValue(UtilsReference.unFriendUser).await()
        println("===================== finish add unFriendNewUserAddToFirstUser")
    }

    private suspend fun setupUnfriend(): String {
        val userId = getFirstUserId()


        UtilsReference.unFriendUser.userId = userId!!
        UtilsReference.unFriendUser.userUnfriendId =  UtilsReference.user.id
        UtilsReference.unFriendUser.userUnfriendUserName = UtilsReference.user.userName
        return userId
    }

    private suspend fun  saveFirstUserId(){
        Firebase.database.reference.
        child("FirstUserId").setValue(FirebaseAuth.getInstance().uid).await()
    }
    private suspend fun getFirstUserId():String?{
     return   Firebase.database.reference.
        child("FirstUserId").get().await().getValue(String::class.java)
    }

    private fun makeToast(context: Context, text: String) {
        println("user data is empty")
        Toast.makeText(context, text, Toast.LENGTH_SHORT)
            .show()
    }

    fun check(id: String): Boolean {
        return id == FirebaseAuth.getInstance().uid
    }

    private fun userValid(user: Users): Boolean {
        return (user.userName.isNotEmpty() && user.email.isNotEmpty()
                && user.password.isNotEmpty())
    }

    override suspend fun uploadImage(uri: Uri, listener:OnSuccessListener<UploadTask.TaskSnapshot> ){
        userRefStorage = FirebaseStorage.getInstance().getReference("images")
        userRefStorage.child(FirebaseAuth.getInstance().uid.toString()).putFile(uri).addOnSuccessListener(listener)
    }

 override suspend  fun addImageUri(listener: OnSuccessListener<Any>){
        usersRef.child(FirebaseAuth.getInstance().uid.toString())
            .child("imageUri").setValue(UtilsReference.user.imageUri).addOnSuccessListener(listener)
    }












}