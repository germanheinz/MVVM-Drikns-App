package com.example.mvvm_drinks.domain

import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.vo.Resource

interface Repository {

    fun getMovies() : Resource<List<Movie>>
}