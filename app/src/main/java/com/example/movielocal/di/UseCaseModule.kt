package com.example.movielocal.di

import com.example.movielocal.data.usecase.GetMovieFromAssetsUseCaseImpl
import com.example.movielocal.data.usecase.GetMovieListUseCaseImpl
import com.example.movielocal.data.usecase.ManageMovieImpl
import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.GetMovieFromAssetsUseCase
import com.example.movielocal.domain.usecase.GetMovieListUseCase
import com.example.movielocal.domain.usecase.ManageMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMovieImpl(repository: MovieRepository): GetMovieListUseCase =
        GetMovieListUseCaseImpl(repository)

    @Provides
    fun providesGetMovieFromAssetsImpl(repository: MovieRepository): GetMovieFromAssetsUseCase =
        GetMovieFromAssetsUseCaseImpl(repository)

    @Provides
    fun provideManageMoviesImpl(
        getMovieListUseCase: GetMovieListUseCase,
        getMovieFromAssetsUseCase: GetMovieFromAssetsUseCase
    ): ManageMovie =
        ManageMovieImpl(getMovieFromAssetsUseCase, getMovieListUseCase)
}