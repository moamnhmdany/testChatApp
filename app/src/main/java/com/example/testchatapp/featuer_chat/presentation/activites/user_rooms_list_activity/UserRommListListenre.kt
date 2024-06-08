package com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.databinding.FragmentLoginBinding
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity.ChatMessangerActivity
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.UsersListAddActivity
import com.example.testchatapp.feature_authetication.presentation.activites.MainActivity
import com.example.testchatapp.feature_authetication.presentation.activites.login_activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRommListListenre {

    private lateinit var layoutManager: RecyclerView.LayoutManager


    fun observeUsers(
        context: Context,
        ui: ActivityUsersChatListBinding,
        lifecycleOwner: LifecycleOwner
    ) {
        UtilsReference.viewModelRoomsList.setRoomList()


        UtilsReference.userRoomList.observe(lifecycleOwner, Observer {
            recycleViewSetting(context, ui)

        })
        UtilsReference.roomList.clear()


    }

    private fun recycleViewSetting(context: Context, ui: ActivityUsersChatListBinding) {
        layoutManager = LinearLayoutManager(context)
        ui.usersChatRoomListRecyclerview.layoutManager = layoutManager
        ui.usersChatRoomListRecyclerview.addItemDecoration(myItemDecoration())
        UtilsReference.chatRoomListAdapter.roomList = UtilsReference.userRoomList
        ui.usersChatRoomListRecyclerview.adapter = UtilsReference.chatRoomListAdapter

    }

    private fun myItemDecoration() = object : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.bottom = 10
        }
    }

    fun openAddFriendListScreen(ui: ActivityUsersChatListBinding, context: Context) {

        ui.btnAddFriend.setOnClickListener {
            val intent = Intent(context, UsersListAddActivity::class.java)
            context.startActivity(intent)

        }
    }

    fun setupNavSideBar(ui: ActivityUsersChatListBinding, activity: Activity): DrawerLayout {
        val drawer_layout = ui.drawerLayout
        val toggle = ActionBarDrawerToggle(
            activity,
            drawer_layout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)

        toggle.syncState()
        return drawer_layout
    }

    fun menuListener(ui: ActivityUsersChatListBinding, context: Context) {

        ui.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.myProfile -> {
                    Toast.makeText(context, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.people -> {
                    Toast.makeText(context, "People", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.log_out -> {
                    logOut()
                    goToLoginActivity(context)
                    Toast.makeText(context, "done log out", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    fun openDrawer(ui: ActivityUsersChatListBinding, drawerLayout: DrawerLayout) {
        ui.btnOpenDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    private fun logOut() {
        FirebaseAuth.getInstance().signOut()

    }

    private fun goToLoginActivity(context: Context) {

        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    fun onBackPressListener(drawerLayout: DrawerLayout): OnBackPressedCallback {
        val listener = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }

        }
        return listener
    }

    fun goTOMessagesActivity(context: Context, user: UserChatRoom) {
        val intent = Intent(context, ChatMessangerActivity::class.java)
        intent.putExtra("userRoomMate",user)
        intent.putExtra("checkClass",1)
        context.startActivity(intent)
    }

    companion object {
        fun showProgrecceBar(ui: ActivityUsersChatListBinding) {
            ui.usersListProgressBar.visibility = View.VISIBLE
        }

        fun hideProgrecceBar(ui: ActivityUsersChatListBinding) {
            ui.usersListProgressBar.visibility = View.INVISIBLE

        }

    }

}

