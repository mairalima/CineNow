package com.devspacecinenow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.devspacecinenow.detail.presentation.MovieDetailViewModel
import com.devspacecinenow.list.presentation.MovieListViewModel
import com.devspacecinenow.ui.theme.CineNowTheme

class MainActivity : ComponentActivity() {
    private val listViewModel: MovieListViewModel by viewModels { MovieListViewModel.Factory }
    private val detailViewModel: MovieDetailViewModel by viewModels{MovieDetailViewModel.Factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineNowTheme {
                // Usando remember para criar estado mutável


                // Container da superfície usando o 'background' do tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    CineNowApp(
                        listViewModel = listViewModel,
                        detailViewModel = detailViewModel
                    )
                }
            }
        }
    }
}

