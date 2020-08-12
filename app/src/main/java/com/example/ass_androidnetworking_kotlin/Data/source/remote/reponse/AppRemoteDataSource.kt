package com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse

import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.source.remote.AppDataSource
import com.example.ass_androidnetworking_kotlin.Data.source.remote.api.AppService

class AppRemoteDataSource private constructor(private val appService: AppService)
    : AppDataSource.Remote{



    override suspend fun login(userName: String, password: String): BaseResponse<LoginResponse>{
        return appService.login(userName,password)
    }

    override suspend fun register(user: User): BaseResponse<RegisterReponse>{
        return appService.register(user)
    }

    override suspend fun getProducts(): BaseResponse<ProductsResponse> {
        return appService.getProducts()
    }

    companion object{
        private var INSTANCE: AppRemoteDataSource?= null
        fun getInstance(appService: AppService): AppRemoteDataSource =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: AppRemoteDataSource(appService).also { INSTANCE = it }
            }

    }
}