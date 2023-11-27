package com.example.testchatapp.feature_authetication.presentation.activites.sign_up_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testchatapp.feature_authetication.domain.use_case.UserOpareations
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class SignUpActivity : AppCompatActivity() {
    private val utilti = Utiles()
    private val viewModel = ViewModelSignUp(this)
    private val action = SignUpActionsListener()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setting
       val ui = utilti.settingSignUp(this,this@SignUpActivity)
       action.signUp(ui,viewModel,this,this)
       action.openLogin(ui,this)
       UserOpareations.checkUserState(this)



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