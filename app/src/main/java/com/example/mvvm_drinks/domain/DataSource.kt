package com.example.mvvm_drinks.domain

import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.data.model.MovieEntity
import com.example.mvvm_drinks.vo.Resource
import com.example.mvvm_drinks.vo.RetrofitClient

interface DataSource {
    suspend fun getMovieByName(movieName : String): Resource<List<Movie>>
    suspend fun saveMovieIntoRoom(movie: MovieEntity)
    suspend fun getFavoritesMovies(): Resource<List<MovieEntity>>
    suspend fun deleteFavoritesMovies(movie: MovieEntity)

}