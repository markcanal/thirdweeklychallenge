package com.example.movielocal.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class MovieDetails(
    val movieTitle: String = "",
    val description: String = "",
    val rating: Double = 0.0,
    val duration: String = "",
    val genre: String = "",
    val releaseDate: String = "",
    val trailer: String = ""
) : Parcelable
