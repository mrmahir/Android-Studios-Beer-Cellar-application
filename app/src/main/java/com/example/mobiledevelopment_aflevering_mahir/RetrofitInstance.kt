package com.example.mobiledevelopment_aflevering_mahir

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: BeerApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://anbo-restbeer.azurewebsites.net/") // Replace with your actual base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApiService::class.java)
    }
}