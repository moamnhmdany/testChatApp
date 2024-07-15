package com.example.testchatapp.featuer_chat.presentation.activites.chat_massenger_activity

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.testchatapp.featuer_chat.domain.use_case.UtilsReference


class PermissionsHandler(val activity: ChatMessengerActivity) {


    fun requestPermissions() {
        if (!UtilsReference.permissionGranted) {
            ActivityCompat.requestPermissions(
                activity,
                UtilsReference.permissions,
                UtilsReference.REQUEST_CODE
            )
        }
    }

    fun checkPermission(): Boolean {
        UtilsReference.permissionGranted = ActivityCompat.checkSelfPermission(
            activity,
            UtilsReference.permissions[0]
        ) == PackageManager.PERMISSION_GRANTED
        return UtilsReference.permissionGranted
    }


}
