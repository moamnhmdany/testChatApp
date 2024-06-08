package com.example.testchatapp.feature_authetication.presentation.activites

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.testchatapp.R
import com.example.testchatapp.feature_authetication.presentation.util.Utiles

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    val settings = Utiles()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = settings.settingMain(this,this@MainActivity)

    }

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_container)
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }
}