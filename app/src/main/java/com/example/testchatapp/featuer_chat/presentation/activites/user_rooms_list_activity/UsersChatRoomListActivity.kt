package com.example.testchatapp.featuer_chat.presentation.activites.user_rooms_list_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.databinding.HeaderNavigationMenuBinding
import com.example.testchatapp.featuer_chat.domain.models.UserChatRoom
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class UsersChatRoomListActivity : AppCompatActivity(),UserRoomIntent {
    private val setting = Utiles()
    private val actions = UserRommListListenre()
    lateinit var drawerLayout : DrawerLayout
    lateinit var ui : ActivityUsersChatListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         ui = setting.settingUserList(this, this)
         val navHeader = setting.settingNavHeader(ui)

        actions.openAddFriendListScreen(ui,this)
        drawerLayout =  actions.setupNavSideBar(ui,this)
        actions.openDrawer(ui, drawerLayout)
        actions.menuListener(ui,this)
        actions.updateUserName(navHeader,this)
        onBackPressedDispatcher.addCallback(this,actions.onBackPressListener(drawerLayout))
        actions.observeUsers(this,ui,this)
        actions.updateUserImage(navHeader, this)
        actions.restMediaPlayer()
        UtilsReference.interfaceChatRoom = this@UsersChatRoomListActivity

    }

    override fun goChat(user: UserChatRoom) {
        actions.goTOMessagesActivity(this,user)

    }


//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}