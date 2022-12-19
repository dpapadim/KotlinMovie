package com.example.movieappinkotlin

import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    fun insert(movie: MovieEntity)
    @Query("SELECT * FROM tbl_movies WHERE id = :id LIMIT 1")
     fun findById(id: Long): MovieEntity?
    @Query("DELETE FROM tbl_movies WHERE id = :id")
     fun delete(id: Long)
    @Query("SELECT * FROM tbl_movies")
    fun getAll(): List<MovieEntity>
}
