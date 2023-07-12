package com.example.gymgenius

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gymgenius.models.Workout
import com.example.gymgenius.repositories.WorkoutRepository

class AddWorkoutActivity : AppCompatActivity() {
    val workoutRepository = WorkoutRepository(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_workout_activity)

        val workoutNameEditText: TextView = findViewById(R.id.workoutNameEditText)
        val addButton: Button = findViewById(R.id.addWorkoutButton)
        addButton.setOnClickListener{
            val newWorkout = Workout(workoutNameEditText.text.toString())
            Log .d("AddWorkoutActivity", newWorkout.name)
            workoutRepository.addWorkout(newWorkout)
            finish()
        }
    }
}