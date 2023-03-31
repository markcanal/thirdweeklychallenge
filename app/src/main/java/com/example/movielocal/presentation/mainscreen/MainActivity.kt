package com.example.movielocal.presentation.mainscreen

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielocal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
        observeLoader()
        initMovieList()
        setContentView(binding.root)
    }

    private fun initMovieList() {
        viewModel.getMovieList()
        binding.mvoList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        observeData()
    }

    private fun observeLoader() {
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                Timber.e("Loading Movie List")
                binding.progress.isVisible = it
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            viewModel.movie.collect { movie ->
                if (movie != null) {
                    movieAdapter.swapItems(movie)
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        initMovieList()
    }
}