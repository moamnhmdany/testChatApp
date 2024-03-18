package com.example.testchatapp.featuer_chat.domain.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
class UsersUnfriend() :Parcelable{
    var userId = ""
    var userUnfriendId = ""
    var userUnfriendUserName = ""

    constructor(parcel: Parcel) : this() {
        userId = parcel.readString().toString()
        userUnfriendId = parcel.readString().toString()
        userUnfriendUserName = parcel.readString().toString()
    }

    override fun describeContents(): Int {
        return Parcelable.CONTENTS_FILE_DESCRIPTOR
    }

    companion object : Parceler<UsersUnfriend> {

        override fun UsersUnfriend.write(p0: Parcel, p1: Int) {

        }

        override fun create(parcel: Parcel): UsersUnfriend {
            return UsersUnfriend(parcel)
        }
    }

}
