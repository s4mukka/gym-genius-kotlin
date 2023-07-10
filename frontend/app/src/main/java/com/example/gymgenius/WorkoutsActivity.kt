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

class WorkoutsActivity : AppCompatActivity() {
    private val workouts = mutableListOf<Workout>() // Lista de treinos
    private lateinit var workoutsRecyclerView: RecyclerView
    private val ADD_WORKOUT_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workouts)

        workoutsRecyclerView = findViewById(R.id.workoutsRecyclerView)
        workoutsRecyclerView.layoutManager = LinearLayoutManager(this)
        workoutsRecyclerView.adapter = WorkoutsAdapter()

        val addButton: Button = findViewById(R.id.button)
        addButton.setOnClickListener{
            val intent = Intent(this, AddWorkoutActivity::class.java)
            this.startActivity(intent)
            Log.d("WorkoutsActivity", "Botão de adicionar treino clicado")
        }
    }

    private inner class WorkoutsAdapter : RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_workout, parent, false)
            Log.d("WorkoutsAdapter", "onCreateViewHolder")
            return WorkoutViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
            val workout = workouts[position]
            holder.bind(workout)
            Log.d("WorkoutsAdapter", "onBindViewHolder")
        }

        override fun getItemCount(): Int {
            Log.d("WorkoutsAdapter", "getItemCount " + workouts.size)
            return workouts.size
        }

        private inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            private val workoutNameTextView: TextView = itemView.findViewById(R.id.workoutNameTextView)

            fun bind(workout: Workout) {
                Log.d("WorkoutViewHolder", "bind")
                workoutNameTextView.text = workout.name
            }
        }
    }
}