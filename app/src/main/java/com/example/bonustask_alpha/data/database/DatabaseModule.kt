package com.example.bonustask_alpha.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideLocalMovieDatabase(@ApplicationContext app: Context): LocalMovieDatabase {
        return Room.databaseBuilder(
            app,
            LocalMovieDatabase::class.java,
            "local_movie_database"
        ).build()
    }

    @Provides
    fun provideLocalMovieDao(localMovieDatabase: LocalMovieDatabase): LocalMovieDao {
        return localMovieDatabase.localMovieDao()
    }
}