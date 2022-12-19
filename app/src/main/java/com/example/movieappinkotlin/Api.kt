package com.example.movieappinkotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api { @GET("movie/popular")

fun getPopularMovies(
    @Query("api_key") apiKey: String = "28cd7bccfa303b2cc9ffb11d4a0b4c22",
    @Query("page") page: Int
): Call<GetMoviesResponse>

}


