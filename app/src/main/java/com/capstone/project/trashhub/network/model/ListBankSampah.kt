package com.capstone.project.trashhub.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListBankSampah (
    @field:SerializedName("Name")
    var name : String,

    @field:SerializedName("FullAddress")
    var fullAddress : String,

    @field:SerializedName("Street")
    var street : String,

    @field:SerializedName("Categories")
    var categories : String,

    @field:SerializedName("Latitude")
    var latitude : String,

    @field:SerializedName("Longitudea")
    var longitude : String,

    @field:SerializedName("Place_Id")
    var id : String,

    @field:SerializedName("Featured_Image")
    var featuredImage : String

): Parcelable