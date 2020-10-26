package com.example.mvvm_drinks.data.model

import com.example.mvvm_drinks.AppDataBase
import com.example.mvvm_drinks.vo.Resource
import com.example.mvvm_drinks.vo.RetrofitClient

class DataSource(private val appDataBase: AppDataBase) {

    suspend fun getMovieByName(movieName : String): Resource<List<Movie>>{
        return Resource.Success(RetrofitClient.webService.searchMovie(movieName).movieList)
    }

    suspend fun saveMovieIntoRoom(movie: MovieEntity){
        appDataBase.movieDao().insertFavoriteMovie(movie)
    }

    suspend fun getFavoritesMovies(): Resource<List<MovieEntity>> {
       return Resource.Success(appDataBase.movieDao().getAllFavoritesMovies())
    }


}