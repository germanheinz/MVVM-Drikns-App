package com.example.mvvm_drinks.domain

import com.example.mvvm_drinks.data.model.DataSource
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.vo.Resource

class RepositoryImpl(private val dataSource: DataSource) : Repository{

    override suspend fun getMovies(movieName : String): Resource<List<Movie>> {
        return dataSource.getMovieByName(movieName)
    }

}