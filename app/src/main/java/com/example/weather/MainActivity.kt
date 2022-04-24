package com.example.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weather.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import okhttp3.Request

public class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            if(binding.textInput.text.toString().isEmpty()){
                Toast.makeText(this, "Please enter your desired city", Toast.LENGTH_SHORT).show()
            }else{

                val intent = Intent(this,ShowInformation::class.java)
                intent.putExtra("cityName",binding.textInput.text.toString())
                startActivity(intent)

            }
        }
    }
}