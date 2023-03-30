package com.example.weaatherapp

import android.telecom.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("data/2.5/weather")
    fun getData(
        @Query("q") Qdata:String,
        @Query("appid") appid:String
    ): retrofit2.Call<ResponseDataClass>

  /*  @POST("akjsdas")
    fun postData(
        @Query("q") qData: String,
        @Body responseDataClass: ResponseDataClass
    ): Void*/
}