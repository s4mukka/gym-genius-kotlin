package com.example.gymgenius.services

import com.example.gymgenius.models.ExerciseWGer
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseService {
    @GET("exercise/search?language=pt-BR")
    open fun searchTerm(
        @Query("term") term: String
    ): Call<JsonObject>
}