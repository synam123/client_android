package com.example.ass_androidnetworking_kotlin.Data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("full_name")
    val fullname: String,
    @SerializedName("user_name")
    val  userName: String,
    @SerializedName("password")
    val passWord: String,
    @SerializedName("role_id")
    val roleID: Int
)