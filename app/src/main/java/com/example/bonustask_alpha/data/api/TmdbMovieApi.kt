package com.example.bonustask_alpha.data.api

import com.example.bonustask_alpha.BuildConfig
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class TmdbMovieApi(private val client: HttpClient) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"

    suspend fun searchMovies(query: String, page: Int): Result<MovieResultDto> {
        return runCatching {
            val response: HttpResponse =
                client.get("https://api.themoviedb.org/3/search/movie") {
                    parameter("query", query)
                    parameter("page", page)
                    parameter("include_adult", false)
                    parameter("language", "en-US")
                    header("accept", "application/json")
                    header("Authorization", "Bearer ${BuildConfig.TMDB_API_KEY}")
                }

            response.body()
        }
    }

    suspend fun getMovieDetails(movieId: Int): Result<Movie> {
        return runCatching {
            val response: HttpResponse =
                client.get("https://api.themoviedb.org/3/movie/$movieId") {
                    parameter("api_key", BuildConfig.TMDB_API_KEY)
                    header("accept", "application/json")
                    header("Authorization", "Bearer ${BuildConfig.TMDB_API_KEY}")
                }

            response.body()
        }
    }
}