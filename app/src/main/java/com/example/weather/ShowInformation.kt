package com.example.weather

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.weather.databinding.ActivityShowInformationBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ShowInformation : AppCompatActivity() {
    lateinit var binding:ActivityShowInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val city = intent.getStringExtra("cityName").toString()

        var client = OkHttpClient()

        var request = Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=2fd5aa9c19e3366a43e594272b67e404&units=metric").build()

        Toast.makeText(this, "${city}", Toast.LENGTH_SHORT).show()

        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val rawResponse = response.body!!.string()
                val jsonObject = JSONObject(rawResponse)

                val name = jsonObject.getString("name")

                val weatherObject = jsonObject.getJSONArray("weather")
                val weather = weatherObject.getJSONObject(0)
                val main = weather.getString("main")
                val description = weather.getString("description")
                val iconWeather = weather.getString("icon")

                val mainObject = jsonObject.getJSONObject("main")
                val temp=mainObject.getDouble("temp")
                val feels_like=mainObject.getDouble("feels_like")
                val temp_min=mainObject.getDouble("temp_min")
                val temp_max=mainObject.getDouble("temp_max")
                val humidity=mainObject.getInt("humidity")

                val cloud = jsonObject.getJSONObject("clouds")
                val cloudiness = cloud.getInt("all")

                val sys = jsonObject.getJSONObject("sys")
                val country=sys.getString("country")

                val api = API(name, main, description, temp, feels_like, temp_min, temp_max, humidity, country)
                Log.d("tagx", "onCreate: ${api.toString()}")

                val imageUrl = "https://openweathermap.org/img/wn/${iconWeather}@2x.png"
                val flagUrl = "https://countryflagsapi.com/png/${country}"

                runOnUiThread {
                    Glide.with(this@ShowInformation).load(imageUrl).into(binding.weather)
                    Glide.with(this@ShowInformation).load(flagUrl).into(binding.flag)
                    binding.name.text = name
                    binding.currentTemp.text = "Current Temp " + temp.toString() +"°C"
                    binding.feelsLike.text = "Feels like " + feels_like.toString() +"°C"
                    binding.tempMin.text = "Min " + temp_min.toString() +"°C"
                    binding.tempMax.text = "Max " + temp_max.toString() +"°C"
                    binding.cloudiness.text = ("Cloudiness: "+cloudiness.toString() + "%")
                    binding.humidity.text = ("Humidity: "+ humidity.toString() + "%")
                    //binding.status.text = ("Status: "+main)
                    binding.description.text = (description)


                }



            }

        })



    }
}