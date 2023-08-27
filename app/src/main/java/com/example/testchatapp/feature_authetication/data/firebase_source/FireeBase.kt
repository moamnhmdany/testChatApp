package com.example.testchatapp.feature_authetication.data.firebase_source
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
class FireeBase():FireBaseDao {


    private  lateinit var auth:FirebaseAuth
    private lateinit var dataBase:FirebaseDatabase
    override fun createUserWithEmailAndPassword(user: Users) {
        if (user.email.isNotEmpty()) {
            auth = Firebase.auth
            println("================> the email is ")
            println(user.email)
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                   val id = task.result.user!!.uid

                    if (task.isComplete) {
                        if (task.isSuccessful)
                        {
                            println("done add new user >>>>>>>>>>>>>>>>>>>>")
                        }
                        println("done add new user ")
                        makeUserRecord(user, id)
                        println("done add data base of user ")
                    } else
                        println("failed add new user")
                }
        }
        else
            println("user data is empty")
}

    private fun makeUserRecord(user:Users, id:String):FirebaseDatabase{
        dataBase = Firebase.database
        val dbRef = dataBase.getReference("Users")
        user.id = id
        dbRef.child(id).setValue(user)
        return  dataBase
    }

}