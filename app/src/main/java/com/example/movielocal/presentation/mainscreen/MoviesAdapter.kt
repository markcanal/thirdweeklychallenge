package com.example.movielocal.presentation.mainscreen

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movielocal.R
import com.example.movielocal.common.Consts.BUNDLE_MOVIE_ID
import com.example.movielocal.common.utils.MovieDiffCallback
import com.example.movielocal.data.entity.MovieEntity
import com.example.movielocal.databinding.MovieCardBinding
import com.example.movielocal.presentation.moviedetails.MovieDescriptionActivity
import javax.inject.Singleton

@Singleton
class MoviesAdapter(private var movieList: MutableList<MovieEntity> = arrayListOf()) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MovieCardBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            binding.apply {
                movie.also { (movieId, movieTitle, description, rating, duration, genre, release, trailer, watchlist, cover) ->
                    txvMovieTitle.text = movieTitle
                    txvMovieDescription.text =
                        context.getString(R.string.movie_detail, duration, genre)
                    txvWatchlistTag.isVisible = watchlist
                    imgMovie.setImageResource(cover.toInt())
                }
                root.setOnClickListener {
                    val intent = Intent(context, MovieDescriptionActivity::class.java)
                    intent.putExtra(BUNDLE_MOVIE_ID, movie.movieId)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        MovieCardBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun swapItems(data: List<MovieEntity>) {
        val movieDiffCallback = MovieDiffCallback(this.movieList, data)
        val diffResult = DiffUtil.calculateDiff(movieDiffCallback)
        this.movieList.clear()
        this.movieList.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }
}