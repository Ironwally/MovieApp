package com.example.bonustask_alpha.data.api

import kotlinx.serialization.Serializable

@Serializable
data class MovieResultDto(
    val results: List<Movie>
)