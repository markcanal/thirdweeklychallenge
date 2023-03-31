package com.example.movielocal.presentation.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MovieDescriptionViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    private val _movie = MutableLiveData<MovieEntity>()
    private val movie: LiveData<MovieEntity> = _movie

    private var _isLoading = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    fun getMovieFromId(id: Int): LiveData<MovieEntity> {
        viewModelScope.launch {
            _isLoading.postValue(true)
            movieRepository.getMoviesById(id).let { movied ->
                movied?.collect {
                    Timber.e("Movie Detail $it")
                    _movie.postValue(it)
                    _isLoading.postValue(false)
                }
            }
        }
        return movie
    }

    private fun update(movie: MovieEntity) {
        viewModelScope.launch {
            movieRepository.updateMovie(movie)
        }
    }

    fun updateWatchList(movie: MovieEntity, toWatchList: Boolean) {
        val upMovie = movie.copy(watchList = toWatchList)
        update(upMovie)
    }
}