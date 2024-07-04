package com.example.testchatapp.featuer_chat.presentation.activites.profile_activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.testchatapp.databinding.ActivityProfileBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.feature_authetication.domain.use_case.UseCaseUploadImage
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

class ProfileActivityListenre {

    private lateinit var auth: FirebaseAuth

    fun saveInfo(context: Context, ui: ActivityProfileBinding){
        if(UtilsReference.imageUri!= null){
           ui.saveTv.setOnClickListener {
               uploadImage(context,ui){
                   hidePrograssBsar(ui)
                   val intent = Intent(context, UsersChatRoomListActivity::class.java)
                   context.startActivity(intent)

               }
           }
        }
    }

    private fun uploadImage(context: Context, ui: ActivityProfileBinding, listener: OnSuccessListener<Any>) {
        showProgressBar(ui)
        val case = UseCaseUploadImage(context)
        case.uploadImage(UtilsReference.imageUri!!,listener)
    }

    fun setupImage(ui: ActivityProfileBinding, activity: ProfileActivity) {
        val pick = pickImage(activity, ui)

        ui.fabImageAdd.setOnClickListener {
            pick.launch("image/*")

        }
    }


    private fun pickImage(
        activity: ProfileActivity ,
        ui: ActivityProfileBinding
    ): ActivityResultLauncher<String> {
        val pickImage =
            activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) {
                    UtilsReference.imageUri = uri
                    println("----------- image uri ${uri.path}")
                    setImage(ui, UtilsReference.imageUri!!)
                }
            }
        return pickImage
    }

    private fun setImage(ui: ActivityProfileBinding, uri: Uri) {
        ui.shapImageView.setImageURI(uri)
    }

    private fun showProgressBar(ui: ActivityProfileBinding) {
        ui.progressBarLog.visibility = View.VISIBLE
    }

    private fun hidePrograssBsar(ui: ActivityProfileBinding) {
        ui.progressBarLog.visibility = View.INVISIBLE

    }

   fun backToMain(context :Context,ui: ActivityProfileBinding){
        ui.cancelTv.setOnClickListener {
            val intent = Intent(context,UsersChatRoomListActivity::class.java)
            context.startActivity(intent)
        }
    }
}