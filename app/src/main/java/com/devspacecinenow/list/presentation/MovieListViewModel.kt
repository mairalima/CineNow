package com.devspacecinenow.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.devspacecinenow.common.data.RetrofitClient
import com.devspacecinenow.common.model.MovieDto
import com.devspacecinenow.common.model.MovieResponse
import com.devspacecinenow.list.data.ListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MovieListViewModel (

   private val listService: ListService) : ViewModel() {

    private val _uiNowPlaying = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiNowPlaying: StateFlow<List<MovieDto>> = _uiNowPlaying

    //TopRated
    private val _uiTopRated = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiTopRated: StateFlow<List<MovieDto>> = _uiTopRated

    //PopularMovies
    private val _uiPopularMovies = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiPopularMovies: StateFlow<List<MovieDto>> = _uiPopularMovies

    //UpcomingMovies
    private val _uiUpcomingMovies = MutableStateFlow<List<MovieDto>>(emptyList())
    val uiUpcomingMovies: StateFlow<List<MovieDto>> = _uiUpcomingMovies


    init {
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()


    }

    private fun fetchNowPlayingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = listService.getNowPlayingMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results
                if (movies != null) {
                    _uiNowPlaying.value = movies // Atualizando estado com os filmes
                }
            } else {
                Log.d("MovieListViewModel", "Request Error:: ${response.errorBody()}")
            }
        }

    }

    private fun fetchTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = listService.getTopRatedMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results
                if (movies != null) {
                    _uiTopRated.value = movies // Atualizando estado com os filmes
                }
            } else {
                Log.d("MovieListViewModel", "Request Error:: ${response.errorBody()}")
            }
        }

    }
        private fun fetchPopularMovies() {
            viewModelScope.launch(Dispatchers.IO) {
                val response = listService.getPopularMovies()
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {
                        _uiPopularMovies.value = movies // Atualizando estado com os filmes
                    }
                } else {
                    Log.d("MovieListViewModel", "Request Error:: ${response.errorBody()}")
                }
            }
        }

        private fun fetchUpcomingMovies() {
            viewModelScope.launch(Dispatchers.IO) {
                val response = listService.getUpcomingMovies()
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {
                        _uiUpcomingMovies.value = movies // Atualizando estado com os filmes
                    }
                } else {
                    Log.d("MovieListViewModel", "Request Error:: ${response.errorBody()}")
                }
            }
        }


        companion object : ViewModelProvider.Factory {
            val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    val listService =
                        RetrofitClient.retrofitInstance.create(ListService::class.java)
                    return MovieListViewModel(
                        listService
                    ) as T
                }
            }
        }

    }
