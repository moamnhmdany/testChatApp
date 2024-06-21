package com.example.testchatapp.featuer_chat.domain.models

import android.os.Parcel
import android.os.Parcelable

class UsersUnfriend() :Parcelable{
    var userId = ""
    var userUnfriendId = ""
    var userUnfriendUserName = ""
    var userUnfriendImageUri = ""

    constructor(parcel: Parcel) : this() {
        userId = parcel.readString().toString()
        userUnfriendId = parcel.readString().toString()
        userUnfriendUserName = parcel.readString().toString()
        userUnfriendImageUri = parcel.readString().toString()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(userUnfriendId)
        parcel.writeString(userUnfriendUserName)
        parcel.writeString(userUnfriendImageUri)
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
