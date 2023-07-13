package com.example.gymgenius.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiClient {
    val api: Retrofit = Retrofit.Builder()
        .baseUrl(Companion.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    companion object {
        private const val BASE_URL = "https://wger.de/api/v2/"
    }
}