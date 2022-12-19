package com.example.movieappinkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieappinkotlin.Movies.MoviesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val MOVIES_FRAGMENT = "movies_fragment"
private const val WATCH_LIST_FRAGMENT = "watch_list_fragment"


class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.movies -> showMoviesFragment()
                R.id.watchlist -> showWatchListFragment()
            }

            return@setOnNavigationItemSelectedListener true
        }

        showMoviesFragment()

    }
    private fun showMoviesFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        watchListFragment?.let { transaction.hide(it) }
        if (fragment == null) {
            transaction.add(R.id.fragment_container, MoviesFragment(), MOVIES_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }

    private fun showWatchListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)

        moviesFragment?.let { transaction.hide(it) }
        if (fragment == null) {
            transaction.add(R.id.fragment_container, WatchListFragment(), WATCH_LIST_FRAGMENT)
        } else {
            transaction.show(fragment)
        }
        transaction.commit()
    }
}



