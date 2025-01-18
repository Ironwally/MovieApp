package com.example.bonustask_alpha.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonustask_alpha.data.MovieRepository
import com.example.bonustask_alpha.data.api.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movieDetails = MutableStateFlow<Movie?>(null)
    val movieDetails: StateFlow<Movie?> = _movieDetails.asStateFlow()

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).collect { movie ->
                _movieDetails.value = movie
            }
        }
    }
}