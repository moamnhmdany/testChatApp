package com.example.testchatapp.featuer_chat.domain.models

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize


class UsersUnfriend() :Parcelable{
    var userId = ""
    var userUnfriendId = ""
    var userUnfriendUserName = ""

    constructor(parcel: Parcel) : this() {
        userId = parcel.readString().toString()
        userUnfriendId = parcel.readString().toString()
        userUnfriendUserName = parcel.readString().toString()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(userUnfriendId)
        parcel.writeString(userUnfriendUserName)
    }
    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    companion object CREATOR : Parcelable.Creator<UsersUnfriend> {
        override fun createFromParcel(parcel: Parcel): UsersUnfriend {
            return UsersUnfriend(parcel)
        }

        override fun newArray(size: Int): Array<UsersUnfriend?> {
            return arrayOfNulls(size)
        }
    }

}
