package com.example.movielocal.domain.repository

import androidx.lifecycle.LiveData
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.data.model.MovieDetails

interface MovieRepository {
    fun saveMovie(movie: MovieDetails)
    fun deleteMovie(movie: MovieDetails)
    suspend fun getAllMoviesOrder(isDesc: Boolean): LiveData<List<MovieEntity>>
    suspend fun getAllMovies(): LiveData<List<MovieEntity>>
}