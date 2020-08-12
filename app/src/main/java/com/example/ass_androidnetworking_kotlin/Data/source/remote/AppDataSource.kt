package com.example.ass_androidnetworking_kotlin.Data.source.remote

import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.BaseResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.LoginResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.ProductsResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.RegisterReponse

interface AppDataSource {
    interface Local {

    }

    interface Remote {
        suspend fun login(userName: String, password: String): BaseResponse<LoginResponse>
        suspend fun register(user: User): BaseResponse<RegisterReponse>
        suspend fun getProducts(): BaseResponse<ProductsResponse>
    }
}