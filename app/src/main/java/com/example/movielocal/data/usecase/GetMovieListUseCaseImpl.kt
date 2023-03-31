package com.example.movielocal.data.usecase

import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieListUseCaseImpl @Inject constructor(private val repository: MovieRepository) :
    GetMovieListUseCase {
    override fun invoke(): Flow<List<MovieEntity>>? = repository.getAllMovies()
}