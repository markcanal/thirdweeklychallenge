package com.example.movielocal.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.movielocal.common.Consts.MOVIE_DATABASE
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.data.repository.MovieRepositoryImpl
import com.example.movielocal.data.source.local.MovieDao
import com.example.movielocal.data.source.local.MovieDatabase
import com.example.movielocal.domain.repository.MovieRepository
import com.example.movielocal.domain.usecase.MovieDataMapper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, MOVIE_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMoviesDao(db: MovieDatabase) = db.movieDao()

    @Provides
    fun provideEntity() = MovieEntity()

    @Provides
    fun provideMapper() = MovieDataMapper()

    @Provides
    fun provideGson() = Gson()

    @Provides
    fun provideMovieRepositories(
        dao: MovieDao,
        mapper: MovieDataMapper,
        application: Application,
        gson: Gson
    ): MovieRepository {
        return MovieRepositoryImpl(dao, mapper, application, gson)
    }
}