package com.example.mvvm_drinks.data.model

import com.example.mvvm_drinks.vo.Resource
import com.example.mvvm_drinks.vo.RetrofitClient

class DataSource {

    suspend fun getMovieByName(movieName : String): Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webService.searchMovie(movieName).movieList)
    }

}