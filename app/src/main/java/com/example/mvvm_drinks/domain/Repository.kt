package com.example.mvvm_drinks.domain

import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.vo.Resource

interface Repository {

    suspend fun getMovies(movieName: String) : Resource<List<Movie>>

}