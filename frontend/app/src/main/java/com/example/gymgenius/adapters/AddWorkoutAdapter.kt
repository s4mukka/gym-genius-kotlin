package com.example.gymgenius.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gymgenius.R
import com.example.gymgenius.models.Exercise
import com.example.gymgenius.models.Workout
import com.example.gymgenius.repositories.WorkoutRepository

class AddWorkoutAdapter(var context: AppCompatActivity) : RecyclerView.Adapter<AddWorkoutAdapter.AddWorkoutViewHolder>() {
    private val workoutRepository = WorkoutRepository(context)
    private val exercises = workoutRepository.getNewWorkout().exercises// Lista de treinos

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddWorkoutViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout, parent, false)
        return AddWorkoutViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddWorkoutViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    inner class AddWorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseNameTextView: TextView = itemView.findViewById(R.id.workoutNameTextView)

        fun bind(exercise: Exercise) {
            exerciseNameTextView.text = exercise.name
        }
    }
}