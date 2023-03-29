package com.example.movielocal.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class MovieList : ArrayList<MovieDetails>(), Parcelable
