package com.example.gymgenius.repositories

import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gymgenius.models.Exercise
import com.example.gymgenius.models.ExerciseWGer
import com.example.gymgenius.services.ApiClient
import com.example.gymgenius.services.ExerciseService
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository(var context: AppCompatActivity) : ApiClient() {
    private val service: ExerciseService = api.create(ExerciseService::class.java)

    suspend fun searchTerm(term: String): List<Exercise> = withContext(Dispatchers.IO) {
        val response = service.searchTerm(term).execute()

        if (response.isSuccessful) {
            val exercisesWGerSuggestions = response.body()?.getAsJsonArray("suggestions")
            val typeToken = object : TypeToken<List<ExerciseWGer>>() {}.type

            val gson = Gson()
            val exercisesWGer = gson.fromJson<List<ExerciseWGer>>(exercisesWGerSuggestions, typeToken)

            return@withContext exercisesWGer?.map { exercise: ExerciseWGer? ->
                Exercise(exercise?.data!!.name, 0, 0)
            } as List<Exercise>
        } else {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
            }
            return@withContext emptyList()
        }
    }
}
