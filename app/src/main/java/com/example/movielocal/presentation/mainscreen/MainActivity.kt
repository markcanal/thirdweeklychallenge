package com.example.movielocal.presentation.mainscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielocal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() :
    AppCompatActivity() {
    private val viewModel: MainScreenViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        movieAdapter = MoviesAdapter()
        initMovieList()
        observeData()
        setContentView(binding.root)
    }

    private fun initMovieList() {
        binding.mvoList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun observeData() {
        viewModel.isLoading.observe(this) {
            binding.progress.isVisible = it
        }
        viewModel.getMovieList().observe(this) { movies ->
            if (movies.isNullOrEmpty()) {
                viewModel.getDataFromAsset()
                Timber.e("No movie list.")
            } else {
                Timber.e("We have movie list.")
                movieAdapter.swapItems(movies)
            }
        }
    }

}