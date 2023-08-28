package com.example.testchatapp.feature_authetication.data.firebase_source
import android.content.Context
import android.widget.Toast
import com.example.testchatapp.feature_authetication.domain.model.Users
import com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity.SignUpActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FireeBase():MyDataBaseDao {


    private  lateinit var user:FirebaseAuth
    private lateinit var dataBase:FirebaseDatabase
    override suspend fun createUserWithEmailAndPassword(user: Users,context: Context) {
        println(checkValidation(user))
        if (checkValidation(user)) {

            this.user = Firebase.auth

            println("================> the email is ")
            println(user.email)
          try {

              this.user.createUserWithEmailAndPassword(user.email, user.password)
                  .addOnCompleteListener { task ->
                      val id = task.result.user!!.uid

                      if (task.isComplete) {
                          if (task.isSuccessful) {
                              println("done add new user >>>>>>>>>>>>>>>>>>>>")
                          }
                          println("done add new user ")
                          makeUserRecord(user, id)
                          println("done add data base of user ")
                      } else
                          println("failed add new user")
                  }
          }catch (e: FirebaseNetworkException){
                  println("network error with = $e")
          }
        }
        else {

            println("user data is empty")
             Toast.makeText(context,"please write your information correctly",Toast.LENGTH_SHORT).show()
              }
}

    private fun checkValidation(user: Users):Boolean {
        return (user.userName.isNotEmpty() &&  user.email.isNotEmpty()
                && user.password.isNotEmpty() )



    }

    private fun makeUserRecord(user:Users, id:String):FirebaseDatabase{
        dataBase = Firebase.database

            try {
                val dbRef = dataBase.getReference("Users")
                user.id = id
                dbRef.child(id).setValue(user)
            }catch (e :Exception){
                println("database error = $e")
            }


        return  dataBase
    }

}