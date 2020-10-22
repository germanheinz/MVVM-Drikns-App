package com.example.mvvm_drinks.domain

import com.example.mvvm_drinks.data.model.DataSource
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.vo.Resource

class RepositoryImpl(private val dataSource: DataSource) : Repository{

    override fun getMovies(): Resource<List<Movie>> {
        return dataSource.generateMoviesList
    }

}