package com.example.weaatherapp

import android.widget.Toast
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object VIewModel {

     fun getData(Qdata: String, appid: String) : ResponseDataClass? {
        var responseDataClass : ResponseDataClass? = ResponseDataClass()
         RetrofitInstance.apiinterface.getData(Qdata, appid)
            .enqueue(object : Callback<ResponseDataClass?> {
                override fun onResponse(
                    call: Call<ResponseDataClass?>,
                    response: Response<ResponseDataClass?>
                ) {
                    responseDataClass = response.body()

                }

                override fun onFailure(call: Call<ResponseDataClass?>, t: Throwable) {
                  //  Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
                    responseDataClass = null
                }
            })
        return responseDataClass
    }
}