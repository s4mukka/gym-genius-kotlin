package com.example.gymgenius.models

data class ExerciseWGer(
    val value: String,
    val data: ExerciseWGerData
)

data class ExerciseWGerData(
    val id: Int,
    val base_id: Int,
    val name: String,
    val category: String,
    val image: String?,
    val image_thumbnail: String?
)
