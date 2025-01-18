package com.example.bonustask_alpha.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bonustask_alpha.data.MovieRepository
import com.example.bonustask_alpha.data.api.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {
    private var page = 1
    private var _uiState = MutableStateFlow(UIState("", emptyList()))
    val uiState: StateFlow<UIState> = _uiState

    fun searchMovies(query: String) {
        page = 1

        _uiState.update { prev ->
            prev.copy(query = query, movies = emptyList())
        }

        viewModelScope.launch {
            repository.searchMovies(query, page).collect { movies ->
                _uiState.update { prev ->
                    prev.copy(movies = movies)
                }
            }
        }
    }

    fun loadNextPage() {
        ++page

        viewModelScope.launch {
            _uiState.update { prev ->
                prev.copy(isLoading = true)
            }

            val current = _uiState.value.movies

            repository.searchMovies(_uiState.value.query, page).collect { new ->
                _uiState.update { prev ->
                    prev.copy(movies = current + new, isLoading = false)
                }
            }
        }
    }

    data class UIState(
        val query: String,
        val movies: List<Movie>,
        val isLoading: Boolean = false
    )
}