package com.example.movieappinkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room


class WatchListFragment : Fragment() {
    private lateinit var watchList: RecyclerView
    private lateinit var watchListAdapter: WatchListAdapter

    private val db: AppDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            AppDatabase::class.java,
            "mymovies.db"
        ).allowMainThreadQueries().build()
    }
    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            getWatchList()
        }
    }
    override fun onResume() {
        super.onResume()
        getWatchList()

    }





    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)
        watchList = view.findViewById(R.id.watchlist)


        watchList.layoutManager = GridLayoutManager(context, 3)
        watchListAdapter = WatchListAdapter(listOf())
        {
            when (it.type) {
                is WatchListType.MovieType -> showMovieDetails(it)

            }
        }
            watchList.adapter = watchListAdapter
        getWatchList()





    return view
    }


    private fun showMovieDetails(item: WatchList) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(MOVIE_ID, item.id)
        intent.putExtra(MOVIE_BACKDROP, item.backdropPath)
        intent.putExtra(MOVIE_POSTER, item.posterPath)
        intent.putExtra(MOVIE_TITLE, item.title)
        intent.putExtra(MOVIE_RATING, item.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, item.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, item.overview)
        startActivity(intent)

    }


    private fun getWatchList() {
        val movies = db.movieDao().getAll()
        val watchList = mutableListOf<WatchList>()
        watchList.addAll(
            movies.map { movie ->
                WatchList(
                    movie.id,
                    movie.title,
                    movie.overview,
                    movie.posterPath,
                    movie.backdropPath,
                    movie.rating,
                    movie.releaseDate,
                    WatchListType.MovieType

                )
            }
        )

        watchListAdapter.updateItems(watchList)
    }

}



