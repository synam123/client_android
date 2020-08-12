package com.example.ass_androidnetworking_kotlin.Data.source.remote.reponse

import com.example.ass_androidnetworking_kotlin.Data.model.Product
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products")
    val products: List<Product> = listOf()
)