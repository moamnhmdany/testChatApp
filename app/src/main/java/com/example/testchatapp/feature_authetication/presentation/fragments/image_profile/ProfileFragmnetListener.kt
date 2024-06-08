package com.example.testchatapp.feature_authetication.presentation.fragments.image_profile

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.testchatapp.databinding.FragmentImageProfileBinding
import com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity.UsersChatRoomListActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragmnetListener {

    private lateinit var auth: FirebaseAuth

    fun checkUser(ui: FragmentImageProfileBinding,activity: FragmentActivity?) {
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            ui.tvSkip.setOnClickListener {
            val intent = Intent(activity, UsersChatRoomListActivity::class.java)
                activity!!.startActivity(intent)
            }
        }
    }



}