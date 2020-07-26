package com.example.ass_androidnetworking_kotlin.Data.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("full_name")
    val fullname: String,
    @SerializedName("user_name")
    val  userName: String,
    val passWord: String,
    val roleID: Int
)