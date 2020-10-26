package com.example.mvvm_drinks.domain

import androidx.room.*
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.data.model.MovieEntity

@Dao
interface MovieDao {

    //We use KTX so we can use suspend in coroutines
    @Query("SELECT * FROM movie_db_room")
    suspend fun getAllFavoritesMovies() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)

}