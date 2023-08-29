package com.example.testchatapp.featuer_chat.presentation.activites.user_chat_list_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.core.view.WindowCompat
import com.example.testchatapp.databinding.ActivityUsersChatListBinding
import com.example.testchatapp.databinding.ActivityUsersChatListBinding.inflate
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class UsersChatListActivity : AppCompatActivity() {
  private  lateinit var binding: ActivityUsersChatListBinding
          val setting = Utiles()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      val ui = setting.settingUserList(this,this)

    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}