package com.example.mvvm_drinks.data.model

import com.example.mvvm_drinks.AppDataBase
import com.example.mvvm_drinks.domain.DataSource
import com.example.mvvm_drinks.domain.MovieDao
import com.example.mvvm_drinks.vo.Resource
import com.example.mvvm_drinks.vo.RetrofitClient
import javax.inject.Inject

class DataSourceImpl @Inject constructor(private val movieDao: MovieDao) : DataSource {

    override suspend fun getMovieByName(movieName : String): Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webService.searchMovie(movieName).movieList)
    }

    override suspend fun saveMovieIntoRoom(movie: MovieEntity){
        movieDao.insertFavoriteMovie(movie)
    }

    override suspend fun getFavoritesMovies(): Resource<List<MovieEntity>> {
       return Resource.Success(movieDao.getAllFavoritesMovies())
    }

    override suspend fun deleteFavoritesMovies(movie: MovieEntity){
        return movieDao.deleteFavoriteMovie(movie)
    }

}