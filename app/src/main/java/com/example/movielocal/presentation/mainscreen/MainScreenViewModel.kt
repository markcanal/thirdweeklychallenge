package com.example.movielocal.presentation.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.domain.usecase.ManageMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val manageMovie: ManageMovie
) :
    ViewModel() {

    private var _movieList = MutableStateFlow<List<MovieEntity>?>(mutableListOf())
    var movie = _movieList.asStateFlow()

    private var _isLoading = MutableStateFlow(false)
    var isLoading = _isLoading.asStateFlow()

    private suspend fun getDataFromAsset() {
        manageMovie.getMovieFromAssets().invoke()
    }

    fun getMovieList() {
        viewModelScope.launch {
            _isLoading.value = true
            manageMovie.getMovieList().invoke().let { movies ->
                movies?.collect {
                    Timber.e(it.toString())
                    if (it.isEmpty()) {
                        _isLoading.value = true
                        getDataFromAsset()
                    } else {
                        _movieList.value = it
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}