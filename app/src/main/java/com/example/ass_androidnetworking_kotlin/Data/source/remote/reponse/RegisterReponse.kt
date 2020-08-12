package com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse

import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.google.gson.annotations.SerializedName

data class RegisterReponse (@SerializedName("user") val user: User)