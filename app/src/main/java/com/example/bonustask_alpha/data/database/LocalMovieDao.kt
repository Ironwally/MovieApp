package com.example.bonustask_alpha.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(movies: List<LocalMovie>)

    @Query("SELECT * FROM movies WHERE id = :uid")
    fun getMovieDetails(uid: Int): Flow<LocalMovie>
}