package com.example.gymgenius.repositories

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.gymgenius.models.Exercise
import com.example.gymgenius.models.Workout
import com.example.gymgenius.services.ApiClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WorkoutRepository(var context: AppCompatActivity): ApiClient() {
    public final fun getWorkouts(): List<Workout> {
        return getWorkoutsFromSharedPreferences()
    }

    public final fun addWorkout(workout: Workout) {
        addWorkoutToSharedPreferences(workout)
    }

    public final fun clearWorkouts() {
        clearWorkoutsInSharedPreferences()
    }

    public final fun getNewWorkout(): Workout {
        return getNewWorkoutFromSharedPreferences()
    }

    public final fun addExerciseToNewWorkout(exercise: Exercise?) {
        addExerciseInNewWorkoutToSharedPreferences(exercise)
    }

    public final fun clearNewWorkout() {
        clearNewWorkoutInSharedPreferences()
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

    private final fun getNewWorkoutFromSharedPreferences(): Workout {
        val sharedPreferences = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
        val workoutJson = sharedPreferences.getString("newWorkout", "")
        val gson = Gson()
        Log.d("GSON", workoutJson.toString())
        if (!workoutJson.isNullOrEmpty()) {
            return gson.fromJson(workoutJson, Workout::class.java)
        }
        return Workout("", mutableListOf<Exercise>())
    }

    private final fun addExerciseInNewWorkoutToSharedPreferences(exercise: Exercise?) {
        val sharedPreferences = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val workout = getNewWorkoutFromSharedPreferences()
        if (exercise != null) {
            workout.exercises.add(exercise)
        }

        // Use a biblioteca Gson ou outra biblioteca de serialização JSON para converter a lista de objetos Workout em uma string JSON
        val gson = Gson()
        val workoutJson = gson.toJson(workout)

        editor.putString("newWorkout", workoutJson)
        editor.apply()
    }

    private final fun clearNewWorkoutInSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("newWorkout")
        editor.apply()
    }

    private final fun clearWorkoutsInSharedPreferences() {
        val sharedPreferences = context.getSharedPreferences("my_session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("workouts")
        editor.apply()
    }
}
