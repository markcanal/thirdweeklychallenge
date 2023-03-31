package com.example.movielocal.data.usecase

import com.example.movielocal.domain.usecase.GetMovieFromAssetsUseCase
import com.example.movielocal.domain.usecase.GetMovieListUseCase
import com.example.movielocal.domain.usecase.ManageMovie
import javax.inject.Inject

class ManageMovieImpl @Inject constructor(
    private val getMovieFromAssetsUseCase: GetMovieFromAssetsUseCase,
    private val getMovieListUseCase: GetMovieListUseCase
) : ManageMovie {
    override fun getMovieList(): GetMovieListUseCase {
        return getMovieListUseCase
    }

    override fun getMovieFromAssets(): GetMovieFromAssetsUseCase {
        return getMovieFromAssetsUseCase
    }
}