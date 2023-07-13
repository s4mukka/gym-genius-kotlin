package com.example.gymgenius.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.gymgenius.AddExerciseActivity
import com.example.gymgenius.R
import com.example.gymgenius.models.Exercise
import com.example.gymgenius.models.Workout
import com.example.gymgenius.repositories.ExerciseRepository
import com.example.gymgenius.repositories.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddExerciseAdapter(
    private val listener: OnItemClickListener,
    private val exercises: MutableList<Exercise>
) : RecyclerView.Adapter<AddExerciseAdapter.AddExerciseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddExerciseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout, parent, false)

        return AddExerciseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AddExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    inner class AddExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val exerciseNameTextView: TextView = itemView.findViewById(R.id.workoutNameTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val exercise = exercises[position]
                    listener.onItemClick(exercise)
                }
            }
        }

        fun bind(exercise: Exercise) {
            exerciseNameTextView.text = exercise.name
        }
    }

    interface OnItemClickListener {
        fun onItemClick(exercise: Exercise)
    }
}