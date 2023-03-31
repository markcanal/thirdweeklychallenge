package com.example.movielocal.domain.usecase

interface ManageMovie {
    fun getMovieList(): GetMovieListUseCase
    fun getMovieFromAssets(): GetMovieFromAssetsUseCase
}