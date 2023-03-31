package com.example.movielocal.presentation.moviedetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.movielocal.R
import com.example.movielocal.common.Consts.BUNDLE_MOVIE_ID
import com.example.movielocal.databinding.ActivityMovieDescriptionBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MovieDescriptionActivity @Inject constructor() : AppCompatActivity() {
    private val viewModel: MovieDescriptionViewModel by viewModels()
    private lateinit var binding: ActivityMovieDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDescriptionBinding.inflate(layoutInflater)
        val intentExtra = this.intent.extras
        val id: Int
        if (intentExtra != null) {
            id = intentExtra.getInt(BUNDLE_MOVIE_ID, 0)
            observeIntent(id)
        } else {
            Toast.makeText(this, "Noting on intent", Toast.LENGTH_LONG).show()
        }

        setContentView(binding.root)
    }

    private fun observeIntent(id: Int) {
        viewModel.getMovieFromId(id).observe(this) { movie ->
            if (movie != null) {
                Timber.e(movie.toString())
                binding.apply {
                    movie.apply {
                        imgMovieCover.setImageResource(cover.toInt())
                        txvRate.text = rating.toString()
                        txvTitle.text = movieTitle
                        txvMovieDetailDescription.text = description
                        txvDetailsGenre.text = genre
                        txvDetailsRelease.text = releaseDate
                        acbAddToWatch.text = if (watchList) {
                            getString(R.string.remove_from_watchlist)
                        } else {
                            getString(R.string.add_to_watchlist)
                        }
                        acbAddToWatch.setOnClickListener {
                            if (acbAddToWatch.text == getString(R.string.remove_from_watchlist)) {
                                viewModel.updateWatchList(movie, false)
                            } else {
                                viewModel.updateWatchList(movie, true)
                            }
                        }
                    }
                }
            }
        }
    }
}

