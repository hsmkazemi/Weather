package com.example.weather

data class API(var name:String ="", var main:String ="", var description:String ="", var temp:Double=0.0, var feels_like:Double=0.0,
               var temp_min:Double=0.0, var temp_max:Double=0.0, var humidity:Int=0, var country:String=""){
    override fun toString(): String {
        return "API(name='$name', main='$main', description='$description', temp=$temp, feels_like=$feels_like, temp_min=$temp_min, temp_max=$temp_max, humidity=$humidity, country='$country')"
    }
}
//