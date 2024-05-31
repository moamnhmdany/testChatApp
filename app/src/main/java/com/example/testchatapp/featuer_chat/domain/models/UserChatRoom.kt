package com.example.testchatapp.featuer_chat.domain.models

import android.os.Parcel
import android.os.Parcelable

class UserChatRoom() : Parcelable {
    var id = ""
    var userName = ""
    var userPictuer = ""
    var userLastMsg = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readString().toString()
        userName = parcel.readString().toString()
        userPictuer = parcel.readString().toString()
        userLastMsg = parcel.readString().toString()
    }    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(userName)
        dest.writeString(userPictuer)
        dest.writeString(userLastMsg)
    }
    companion object CREATOR : Parcelable.Creator<UserChatRoom> {
        override fun createFromParcel(parcel: Parcel): UserChatRoom {
            return UserChatRoom(parcel)
        }

        override fun newArray(size: Int): Array<UserChatRoom?> {
            return arrayOfNulls(size)
        }
    }
}