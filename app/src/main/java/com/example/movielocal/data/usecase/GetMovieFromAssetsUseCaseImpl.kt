package com.example.movielocal.data.usecase

import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.GetMovieFromAssetsUseCase
import javax.inject.Inject

class GetMovieFromAssetsUseCaseImpl @Inject constructor(private val repository: MovieRepository) :
    GetMovieFromAssetsUseCase {
    override suspend fun invoke() = repository.setMovieFromAsset()
}