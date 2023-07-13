package com.example.gymgenius.models

data class Workout(
    var name: String,
    val exercises: MutableList<Exercise>
)
