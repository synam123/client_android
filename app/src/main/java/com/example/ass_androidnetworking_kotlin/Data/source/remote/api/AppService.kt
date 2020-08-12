package com.example.ass_androidnetworking_kotlin.Data.source.remote.api

import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.BaseResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.LoginResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.ProductsResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.RegisterReponse
import retrofit2.http.*

interface AppService {
    @FormUrlEncoded
    @POST("/api/user/login")
    suspend fun login(
        @Field("user_name") userName: String,
        @Field("password") password: String
    ): BaseResponse<LoginResponse>


    @POST("/api/user/register")
    suspend fun register(@Body Users: User): BaseResponse<RegisterReponse>

    @GET("/api/product/all")
    suspend fun getProducts(): BaseResponse<ProductsResponse>
}