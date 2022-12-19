package com.example.movieappinkotlin.Movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieappinkotlin.Movie
import com.example.movieappinkotlin.R

class MoviesAdapter(
    private var movies: List<Movie>,
    private val onMovieClick: (movie: Movie) -> Unit,
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    fun getMovies(): List<Movie> {
        return this.movies
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun updateMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val poster: ImageView = itemView.findViewById(R.id.item_movie_poster)


        fun bind(movie: Movie) {

            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                .transform(CenterCrop())
                .into(poster)

            itemView.setOnClickListener {
                onMovieClick.invoke(movie)
            }
        }
    }
}