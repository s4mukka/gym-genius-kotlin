package com.example.gymgenius

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymgenius.adapters.AddWorkoutAdapter
import com.example.gymgenius.adapters.WorkoutsAdapter
import com.example.gymgenius.models.Exercise
import com.example.gymgenius.models.Workout
import com.example.gymgenius.repositories.ExerciseRepository
import com.example.gymgenius.repositories.WorkoutRepository

class AddWorkoutActivity : AppCompatActivity() {
    private val workoutRepository = WorkoutRepository(this)
    private lateinit var workoutsRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_workout_activity)

        workoutRepository.addExerciseToNewWorkout(null)

        val workoutNameEditText: TextView = findViewById(R.id.workoutNameEditText)
        val addButton: Button = findViewById(R.id.addWorkoutButton)
        addButton.setOnClickListener{
            val newWorkout = workoutRepository.getNewWorkout()
            newWorkout.name = workoutNameEditText.text.toString()
            workoutRepository.addWorkout(newWorkout)
            finish()
        }

        val addExerciseButton: Button = findViewById(R.id.addExercise)
        addExerciseButton.setOnClickListener{
            val intent = Intent(this, AddExerciseActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        workoutsRecyclerView = findViewById(R.id.exercises)
        workoutsRecyclerView.layoutManager = LinearLayoutManager(this)
        workoutsRecyclerView.adapter = AddWorkoutAdapter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        workoutRepository.clearNewWorkout()
    }
}