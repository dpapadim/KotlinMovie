package com.example.movieappinkotlin

data class WatchList(
    val id: Long,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val rating: Float,
    val releaseDate: String,
    val type: WatchListType
)

