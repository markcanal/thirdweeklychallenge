package com.example.movielocal.presentation.mainscreen

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.movielocal.common.base.BaseActivity
import com.example.movielocal.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor() :
    BaseActivity<ActivityMainBinding>() {
    private val viewModel: MainScreenViewModel by viewModels()
    override val binding: ActivityMainBinding
        get() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observer()
    }

    private fun observer() {
        viewModel.isLoading.observe(this, Observer {
            Timber.d("Loading: $it")
            binding.progress.visibility = View.GONE
            binding.mvoList.isVisible = it == false
        })
        viewModel.movie.observe(this, Observer {
            val size = it?.size ?: 0
            if (size > 0) {
                Timber.d("movieObserve ${it?.get(0).toString()}")

            } else {
                Timber.d("movieObserve No movie")
            }
        })
    }
}