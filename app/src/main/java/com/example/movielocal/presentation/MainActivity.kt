package com.example.movielocal.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movielocal.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("Created ")
    }
}