package com.example.mypixabay.model


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaApi {
    @GET("api/")
    fun getImages(
        @Query("key") key: String = "30876582-6c5c0bd80594da8e441608add",
        @Query("q") keyWord: String,
        @Query("page")page: Int = 1,
        @Query("per_page")perPege:Int =5
    ): Call<PixaModel>
}