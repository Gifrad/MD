package com.capstone.project.trashhub.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message (
    var name : String,
    var profilePic : String,
    var email : String,
    var lastMessage : String,
    var unseenMessage : Int,
):Parcelable