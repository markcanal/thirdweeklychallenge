package com.example.movielocal.data.repository

import com.example.movielocal.data.model.MovieDetails
import com.example.movielocal.data.source.local.MovieDao
import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.MovieDataMapper

class MovieRepositoryImpl(private val dao: MovieDao, private val mapper: MovieDataMapper) :
    MovieRepository {
    override fun saveMovie(movie: MovieDetails) = dao.insertMovie(mapper.getMovieEntity(movie))
    override fun deleteMovie(movie: MovieDetails) = dao.deleteMovie(mapper.getMovieEntity(movie))
    override suspend fun getAllMoviesOrder(isDesc: Boolean) =
        if (isDesc) {
            dao.getAllMoviesByOrderDesc()
        } else {
            dao.getAllMoviesByOrderAsc()
        }

    override suspend fun getAllMovies() = dao.getAllMoviesByOrderAsc()

}