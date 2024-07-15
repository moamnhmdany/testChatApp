package com.example.testchatapp.feature_authetication.presentation.fragments.image_profile_fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.testchatapp.databinding.FragmentImageProfileBinding
import com.example.testchatapp.databinding.FragmentLoginBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.example.testchatapp.feature_authetication.domain.use_case.UseCaseUploadImage
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

class ProfileFragmnetListener {

    private lateinit var auth: FirebaseAuth


    private fun uploadImage(context: Context,ui: FragmentImageProfileBinding,listener: OnSuccessListener<Any>) {
        showProgressBar(ui)
        val case = UseCaseUploadImage(context)
        case.uploadImage(UtilsReference.imageUri!!,listener)
    }



    fun setupImage(ui: FragmentImageProfileBinding, imageProfileFragment: ImageProfileFragment) {
        val pick = pickImage(imageProfileFragment, ui)

        ui.fabImageAdd.setOnClickListener {
            pick.launch("image/*")

        }
    }

    private fun setImage(ui: FragmentImageProfileBinding, uri: Uri) {
        ui.shapImageView.setImageURI(uri)
    }

    private fun pickImage(
        imageProfileFragment: ImageProfileFragment,
        ui: FragmentImageProfileBinding
    ): ActivityResultLauncher<String> {
        val pickImage =
            imageProfileFragment.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
                if (uri != null) {
                    UtilsReference.imageUri = uri
                    println("----------- image uri ${uri.path}")
                    setImage(ui, UtilsReference.imageUri!!)
                }
            }
        return pickImage
    }

    fun goUserListPage(ui: FragmentImageProfileBinding, activity: FragmentActivity?) {
        ui.tvSkip.setOnClickListener {
            val intent = Intent(activity, UsersChatRoomListActivity::class.java)
            activity!!.startActivity(intent)
        }
    }

    fun goUserListPageNextButton(
        ui: FragmentImageProfileBinding,
        activity: FragmentActivity?,
        context: Context?
    ) {
        ui.btnNext.setOnClickListener {
            uploadImage(context!!,ui){
                hidePrograssBsar(ui)
                val intent = Intent(activity, UsersChatRoomListActivity::class.java)
                activity!!.startActivity(intent)
            }

        }
    }
    private fun showProgressBar(ui: FragmentImageProfileBinding) {
        ui.progressBarLog.visibility = View.VISIBLE
    }

    private fun hidePrograssBsar(ui: FragmentImageProfileBinding) {
        ui.progressBarLog.visibility = View.INVISIBLE

    }
}
