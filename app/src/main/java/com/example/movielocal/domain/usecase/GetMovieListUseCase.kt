package com.example.movielocal.domain.usecase

import com.example.movielocal.data.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface GetMovieListUseCase {
    operator fun invoke(): Flow<List<MovieEntity>>?
}