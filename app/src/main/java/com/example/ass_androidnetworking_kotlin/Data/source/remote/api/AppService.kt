package com.example.ass_androidnetworking_kotlin.Data.source.remote.api

import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.BaseResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.LoginResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.RegisterReponse
import retrofit2.http.Field
import retrofit2.http.POST

interface AppService {
    @POST("")
    suspend fun login(
        @Field("usser_name") userName:String,
        @Field ("pass_word") passWord:String
    ) : BaseResponse<LoginResponse>
    @POST("")
    suspend fun register(Users:User): BaseResponse<RegisterReponse>
}