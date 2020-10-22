package com.example.mvvm_drinks.data.model

import com.example.mvvm_drinks.vo.Resource

class DataSource {

    val generateMoviesList = Resource.Success(listOf<Movie>(
        Movie("imageFromDataSource", "nameFromDataSource", "Description"),
        Movie("imageFromDataSource", "nameFromDataSource", "Description"),
        Movie("imageFromDataSource", "nameFromDataSource", "Description"),
        Movie("imageFromDataSource", "nameFromDataSource", "Description"))
    )

//    fun getMoviesList() : Resource<List<Movie>>{
//        return Resource.Success(generateMoviesList)
//    }
}