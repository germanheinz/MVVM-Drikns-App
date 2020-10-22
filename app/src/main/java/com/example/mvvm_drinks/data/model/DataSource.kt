package com.example.mvvm_drinks.data.model

import com.example.mvvm_drinks.vo.Resource

class DataSource {

    private val generateMoviesList = listOf<Movie>(
        Movie("imageFromDataSource", "nameFromDataSource"),
        Movie("imageFromDataSource", "nameFromDataSource"),
        Movie("imageFromDataSource", "nameFromDataSource"),
        Movie("imageFromDataSource", "nameFromDataSource"))


    fun getMoviesList() : Resource<List<Movie>>{
        return Resource.Success(generateMoviesList)
    }
}