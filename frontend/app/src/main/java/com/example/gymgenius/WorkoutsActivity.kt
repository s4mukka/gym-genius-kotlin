package com.example.gymgenius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymgenius.adapters.WorkoutsAdapter
import com.example.gymgenius.models.Workout
import com.example.gymgenius.repositories.WorkoutRepository

class WorkoutsActivity : AppCompatActivity() {
    private lateinit var workoutsRecyclerView: RecyclerView
    private val ADD_WORKOUT_REQUEST_CODE = 1
    private val workoutRepository = WorkoutRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workouts)

        val addButton: Button = findViewById(R.id.button)
        addButton.setOnClickListener{
            val intent = Intent(this, AddWorkoutActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        workoutsRecyclerView = findViewById(R.id.workoutsRecyclerView)
        workoutsRecyclerView.layoutManager = LinearLayoutManager(this)
        workoutsRecyclerView.adapter = WorkoutsAdapter(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        workoutRepository.clearWorkouts()
    }
}