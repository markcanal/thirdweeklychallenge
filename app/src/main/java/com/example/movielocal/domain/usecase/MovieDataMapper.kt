package com.example.movielocal.domain.usecase

import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.data.model.MovieDetails

class MovieDataMapper {
    fun getMovieData(entity: MovieEntity): MovieDetails {
        return MovieDetails(
            entity.movieTitle,
            entity.description,
            entity.rating,
            entity.duration,
            entity.genre,
            entity.releaseDate,
            entity.trailer
        )
    }

    fun getMovieEntity(details: MovieDetails, cover: String?): MovieEntity {
        return MovieEntity(
            null,
            details.movieTitle,
            details.description,
            details.rating,
            details.duration,
            details.genre,
            details.releaseDate,
            details.trailer,
            watchList = false,
            cover ?: ""
        )
    }
}