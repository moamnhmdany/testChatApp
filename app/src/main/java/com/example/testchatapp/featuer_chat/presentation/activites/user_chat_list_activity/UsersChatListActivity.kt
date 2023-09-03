package com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class UsersChatListActivity : AppCompatActivity() {
    private val setting = Utiles()
    private val actions = UsersListListenre()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = setting.settingUserList(this, this)

        actions.observeUsers(this,this, ui)
        actions.addFriend(ui,this)
    }


//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}