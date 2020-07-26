package com.example.ass_androidnetworking_kotlin.Data.repository

import com.example.ass_androidnetworking_kotlin.Data.model.User
import com.example.ass_androidnetworking_kotlin.Data.source.remote.AppDataSource
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.BaseResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.LoginResponse
import com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse.RegisterReponse

class AppRepository private constructor(
    private val remote: AppDataSource.Remote
){
    suspend fun login(userName:String , password:String): BaseResponse<LoginResponse>{
        return  remote.login(userName,password)
    }
    suspend fun register(user:User):BaseResponse<RegisterReponse>{
        return remote.register(user)
    }
    companion object{
        private var INSTANCE: AppRepository?= null
        fun  getInstance(remote: AppDataSource.Remote)=
            INSTANCE?: synchronized(this){
                INSTANCE?: AppRepository(remote).also { INSTANCE= it }
            }
    }
}