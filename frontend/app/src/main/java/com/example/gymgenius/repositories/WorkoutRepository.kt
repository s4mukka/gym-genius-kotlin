package com.example.gymgenius.repositories

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.gymgenius.models.Workout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WorkoutRepository(var context: AppCompatActivity) {
    public final fun getWorkouts(): List<Workout> {
        return getWorkoutsFromSharedPreferences()
    }

    public final fun addWorkout(workout: Workout) {
        addWorkoutToSharedPreferences(workout)
    }

    private final fun getWorkoutsFromSharedPreferences(): List<Workout> {
        val sharedPreferences = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
        val workoutJson = sharedPreferences.getString("workouts", "")
        val workoutList: List<Workout> = if (workoutJson.isNullOrEmpty()) {
            emptyList()
        } else {
            // Use a bi.blioteca Gson ou outra biblioteca de serialização JSON para desserializar o JSON em uma lista de objetos Workout
            val gson = Gson()
            val type = object : TypeToken<List<Workout>>() {}.type
            gson.fromJson(workoutJson, type)
        }
        return workoutList
    }

    private final fun addWorkoutToSharedPreferences(workout: Workout) {
        val sharedPreferences = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val workoutList = ArrayList<Workout>(getWorkoutsFromSharedPreferences())
        workoutList.add(workout)

        // Use a biblioteca Gson ou outra biblioteca de serialização JSON para converter a lista de objetos Workout em uma string JSON
        val gson = Gson()
        val workoutJson = gson.toJson(workoutList)

        editor.putString("workouts", workoutJson)
        editor.apply()
    }
}
