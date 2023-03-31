package com.example.movielocal.common.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movielocal.data.entity.MovieEntity

class MovieDiffCallback(
    private val mOldList: List<MovieEntity>,
    private val mNewList: List<MovieEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize() = mOldList.size

    override fun getNewListSize() = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldList[oldItemPosition].movieId == mNewList[newItemPosition].movieId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMovieDetails = mOldList[oldItemPosition]
        val newMovieDetails = mNewList[newItemPosition]
        return oldMovieDetails.movieTitle == newMovieDetails.movieTitle && oldMovieDetails.movieId == newMovieDetails.movieId
    }

}
