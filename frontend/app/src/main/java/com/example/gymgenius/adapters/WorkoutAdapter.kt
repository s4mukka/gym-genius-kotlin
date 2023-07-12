package com.example.gymgenius.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gymgenius.R
import com.example.gymgenius.models.Workout
import com.example.gymgenius.repositories.WorkoutRepository

class WorkoutsAdapter(var context: AppCompatActivity) : RecyclerView.Adapter<WorkoutsAdapter.WorkoutViewHolder>() {
    private val workoutRepository = WorkoutRepository(context)
    private val workouts = workoutRepository.getWorkouts()// Lista de treinos

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

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val workoutNameTextView: TextView = itemView.findViewById(R.id.workoutNameTextView)

        fun bind(workout: Workout) {
            Log.d("WorkoutViewHolder", "bind")
            workoutNameTextView.text = workout.name
        }
    }
}