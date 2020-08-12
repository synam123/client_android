package com.example.ass_androidnetworking_kotlin.Data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Product (
    @SerializedName("_id")
    val id: String ="",
    @SerializedName("title")
    val title: String ="",
    @SerializedName("deception")
    val deception: String ="",
    @SerializedName("image_Path")
    val imagePath: String ="",
    @SerializedName("date")
    val date: String =""

) : Parcelable