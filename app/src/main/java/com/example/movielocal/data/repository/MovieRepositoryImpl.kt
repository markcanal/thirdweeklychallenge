package com.example.movielocal.data.repository

import android.app.Application
import com.example.movielocal.R
import com.example.movielocal.common.utils.MovieFileUtils
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.data.model.MovieDetails
import com.example.movielocal.data.model.MovieList
import com.example.movielocal.data.source.local.MovieDao
import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.MovieDataMapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

class MovieRepositoryImpl(
    private val dao: MovieDao,
    private val mapper: MovieDataMapper,
    private val application: Application,
    private val gson: Gson
) :
    MovieRepository {
    override suspend fun saveMovie(movie: MovieDetails, cover: String?) =
        dao.insertMovie(mapper.getMovieEntity(movie, cover))

    override suspend fun deleteMovie(movie: MovieDetails, cover: String?) =
        dao.deleteMovie(mapper.getMovieEntity(movie, cover))

    override fun getAllMoviesOrder(isDesc: Boolean) =
        if (isDesc) {
            dao.getAllMoviesByOrderDesc()
        } else {
            dao.getAllMoviesByOrderAsc()
        }

    override fun getAllMovies() = dao.getAllMoviesByOrderDesc()
    override fun getMoviesById(id: Int): Flow<MovieEntity>? = dao.getMovieById(id)
    override suspend fun updateMovie(movieEntity: MovieEntity) = dao.updateMovie(movieEntity)

    override suspend fun deleteAllMovies() = dao.deleteAllMovie()

    override suspend fun setMovieFromAsset() {
        val jsonString =
            MovieFileUtils.getJsonFromString(application.applicationContext, "movie.json")
        Timber.d("Created from json   $jsonString")
        val listOfMovie = object : TypeToken<MovieList>() {}.type
        val movieList: MovieList = gson.fromJson(jsonString, listOfMovie)
        if (movieList.size > 0) {
            var cover = ""
            for (i in movieList) {
                when (i.movieTitle) {
                    "Tenet" -> cover = R.drawable.tenet.toString()
                    "Spider-Man: Into the Spider-Verse" -> cover = R.drawable.spider_man.toString()
                    "Knives Out" -> cover = R.drawable.knives_out.toString()
                    "Guardians of the Galaxy" -> cover =
                        R.drawable.guardians_of_the_galaxy.toString()
                    "Avengers: Age of Ultron" -> cover = R.drawable.avengers.toString()
                    else -> {}
                }
                Timber.i("Movie detail ready for saving $i cover: $cover")
                dao.insertMovie(mapper.getMovieEntity(i, cover))
            }
        }
    }

}