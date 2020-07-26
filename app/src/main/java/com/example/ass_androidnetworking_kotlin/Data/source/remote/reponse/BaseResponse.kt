package com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse

import com.google.gson.annotations.SerializedName

class BaseResponse<Data: Any>

    (
    @SerializedName("data") private  val data: Data,
    @SerializedName("messages")  val messages: String,
    @SerializedName("status_code")  val statusCode: Int,
    @SerializedName("success")  val success: Boolean
 ){
    fun unwrap(): Data {
        return if (statusCode == 200) data
         else
            throw  Throwable("Response is not success")

    }
}