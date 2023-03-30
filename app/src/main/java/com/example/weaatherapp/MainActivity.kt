package com.example.weaatherapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.weaatherapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso;
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnData.setOnClickListener {
            getData(binding.etPlace.text.toString(), "1e77f60a847385bd30a4b2fcc64b93a9")
            /* val myText = binding.etCity.text
             binding.tvTemp.text = myText*/
            // Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
        }

    }

    private fun getData(Qdata: String, appid: String) {
        RetrofitInstance.apiinterface.getData(Qdata, appid)
            .enqueue(object : Callback<ResponseDataClass?> {
                override fun onResponse(
                    call: Call<ResponseDataClass?>,
                    response: Response<ResponseDataClass?>
                ) {
                    binding.tvCityResult.text = response.body()?.name.toString()
                    binding.tvTempResult.text =
                        response.body()?.main?.temp?.minus(273.15)
                            ?.let { Math.round(it).toString() + " " + "°C" }
                    binding.tvHumidityResult.text = response.body()?.main?.humidity.toString()
                    binding.tvMainResult.text =
                        response.body()?.weather?.get(0)?.description.toString()
                    /*response.body().weather.forEach {
                        binding.tvHumidityResult.text = it.main
                     }*/

                    val urlOriginal = "http://openweathermap.org/img/wn/" + response.body()!!.weather.get(0).icon + ".png"
                    Log.d("activity", "onResponse: image $urlOriginal")
                    setImage(urlOriginal)
                }

                override fun onFailure(call: Call<ResponseDataClass?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
                }
            })
    }
    private fun setImage(url:String){
        Glide.with(this).load(url)
            .into(binding.ivImageView)
         //   .with(this).load(url).into(binding.ivImageView)

    }
}

