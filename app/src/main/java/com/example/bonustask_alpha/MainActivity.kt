package com.example.bonustask_alpha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.bonustask_alpha.ui.NavGraph
import com.example.bonustask_alpha.data.api.TmdbMovieApi
import com.example.bonustask_alpha.ui.theme.Bonustask_alphaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var tmdbMovieApi: TmdbMovieApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Bonustask_alphaTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NavGraph(baseUrl = tmdbMovieApi.baseUrl)
                }
            }
        }
    }
}