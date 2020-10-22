package com.example.mvvm_drinks.domain

import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.data.model.MovieList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    // SUSPEND IT'S FROM COROUTINE, WHEN YOU GET DONE RETURN ME A LIST
    @GET("top_rated?api_key=81f888823f079b9941a4534f90d59f34&language=en-US&page=1")
    suspend fun getMovies() : MovieList

    @GET("movie?api_key=81f888823f079b9941a4534f90d59f34&language=en-US&page=1&include_adult=false&query=")
    suspend fun searchMovie(@Query(value="query") movieName : String) : MovieList

}