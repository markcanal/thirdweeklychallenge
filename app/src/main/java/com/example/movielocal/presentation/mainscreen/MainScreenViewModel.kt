package com.example.movielocal.presentation.mainscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.MovieDataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val dataMapper: MovieDataMapper
) :
    ViewModel() {

    private var _movieList = MutableLiveData<List<MovieEntity>?>()
    private var movie: LiveData<List<MovieEntity>?> = _movieList

    private var _isLoading = MutableLiveData(false)
    var isLoading: LiveData<Boolean> = _isLoading

    fun getDataFromAsset() {
        viewModelScope.launch {
            movieRepository.setMovieFromAsset()
        }
    }

    fun getMovieList(): LiveData<List<MovieEntity>?> {
        viewModelScope.launch {
            _isLoading.postValue(true)
            movieRepository.getAllMovies().let { movies ->
                movies?.collect {
                    _movieList.postValue(it)
                    _isLoading.postValue(false)
                }
            }
        }
        return movie
    }
}