package com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testchatapp.R
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.featuer_chat.presentation.activites.users_lists_add_friend.UsersListAddActivity
import com.example.testchatapp.feature_authetication.presentation.activites.login_activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UsersListListenre {
    lateinit var model: ViewModelUsersList
    val listAdapter = UtilsReference.chatListAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val  usersList = UtilsReference.mutableUsersList

    fun observeUsers(owner: ViewModelStoreOwner,  context: Context, ui: ActivityUsersChatListBinding) {
        recycleViewSetting(context, ui)
        model.getUsers()
    }

    private fun recycleViewSetting(context: Context, ui: ActivityUsersChatListBinding) {
        layoutManager = LinearLayoutManager(context)
        listAdapter.userList = usersList
        ui.usersChatListRecyclerview.layoutManager = layoutManager
        ui.usersChatListRecyclerview.adapter = listAdapter
    }

    fun addFriend(ui: ActivityUsersChatListBinding,context:Context) {

        ui.btnAddFriend.setOnClickListener {
            val intent = Intent(context, UsersListAddActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun setupNavSideBar(ui: ActivityUsersChatListBinding,activity: Activity):DrawerLayout{
        val drawer_layout = ui.drawerLayout
        val toggle = ActionBarDrawerToggle(
            activity, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)

        toggle.syncState()
        return drawer_layout
    }
    fun menuListener(ui: ActivityUsersChatListBinding,context: Context){

        ui.navView.setNavigationItemSelectedListener{
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
    fun openDrawer(ui: ActivityUsersChatListBinding,drawerLayout: DrawerLayout){
        ui.btnOpenDrawer.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }
    private fun logOut(){
        FirebaseAuth.getInstance().signOut()

    }
    private fun goToLoginActivity(context: Context){
        val intent = Intent(context,LoginActivity::class.java)
        context.startActivity(intent)
    }
    fun onBackPressListener(drawerLayout:DrawerLayout):OnBackPressedCallback{
        val listener = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
            }

        }
        return  listener
    }
companion object{
    fun showProgrecceBar(ui: ActivityUsersChatListBinding){
        ui.usersListProgressBar.visibility = View.VISIBLE
    }
    fun hideProgrecceBar(ui: ActivityUsersChatListBinding){
        ui.usersListProgressBar.visibility = View.INVISIBLE

    }

}

}

