package com.example.mvvm_drinks.domain

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_drinks.data.model.MovieEntity

interface MovieDao {

    //We use KTX so we can use suspend in coroutines
    @Query("SELECT * FROM movie_table")
    suspend fun getAllFavoritesMovies() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: MovieEntity)

}