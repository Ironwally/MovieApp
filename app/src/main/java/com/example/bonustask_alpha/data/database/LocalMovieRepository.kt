package com.example.bonustask_alpha.data.database

import com.example.bonustask_alpha.data.api.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class LocalMovieRepository @Inject constructor(
    private val localMovieDao: LocalMovieDao
) {
    fun addMovies(movies: List<Movie>): Flow<Unit> = flow {
        localMovieDao.addMovies(movies.map { movie ->
            LocalMovie(
                id = movie.id,
                title = movie.title,
                overview = movie.overview,
                posterPath = movie.posterPath,
                voteAverage = movie.voteAverage
            )
        })
        emit(Unit)
    }.flowOn(Dispatchers.IO)

    fun getMovieDetails(id: Int): Flow<Movie> {
        return localMovieDao.getMovieDetails(id).map { localMovie ->
            Movie(
                id = localMovie.id,
                title = localMovie.title,
                overview = localMovie.overview,
                posterPath = localMovie.posterPath,
                voteAverage = localMovie.voteAverage
            )
        }
    }
}