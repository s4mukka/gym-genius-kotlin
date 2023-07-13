package com.example.gymgenius

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gymgenius.adapters.AddExerciseAdapter
import com.example.gymgenius.models.Exercise
import com.example.gymgenius.repositories.ExerciseRepository
import com.example.gymgenius.repositories.WorkoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddExerciseActivity : AppCompatActivity(), AddExerciseAdapter.OnItemClickListener {
    private lateinit var exercisesRecyclerView: RecyclerView
    val exerciseRepository = ExerciseRepository(this)
    val workoutRepository = WorkoutRepository(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)



        val searchView: SearchView = findViewById(R.id.searchExercise)

        exercisesRecyclerView = findViewById(R.id.exercises)
        exercisesRecyclerView.layoutManager = LinearLayoutManager(this)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(msg: String?): Boolean {
                if (!msg.isNullOrEmpty()) {
                    GlobalScope.launch(Dispatchers.Main) {
                        try {
                            var exercises = exerciseRepository.searchTerm(msg).toMutableList()
                            exercisesRecyclerView.adapter = createAdapter(exercises)
                            Log.d("exercises", exercises.toString())
                        } catch (e: Exception) {
                            // Lide com a exceção, se necessário
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.

                return false
            }
        })
    }

    private fun createAdapter(exercises: MutableList<Exercise>): AddExerciseAdapter {
        return AddExerciseAdapter(this, exercises)
    }

    override fun onItemClick(item: Exercise) {
        workoutRepository.addExerciseToNewWorkout(item)
        finish()
    }
}