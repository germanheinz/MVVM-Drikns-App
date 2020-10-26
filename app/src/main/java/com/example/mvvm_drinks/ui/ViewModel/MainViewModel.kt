package com.example.mvvm_drinks.ui.ViewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.mvvm_drinks.data.model.Movie
import com.example.mvvm_drinks.data.model.MovieEntity
import com.example.mvvm_drinks.domain.Repository
import com.example.mvvm_drinks.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel @ViewModelInject constructor(private val repository: Repository) : ViewModel() {

    private val movieData = MutableLiveData<String>()

    init {
        setMovieName("batman")
    }

    val fetchMovieList = movieData.distinctUntilChanged().switchMap { movie ->
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try {
                emit(repository.getMovies(movie))
            }catch (e: Exception){
                emit(Resource.Failure(e))
            }
        }
    }

    fun setMovieName(movieName : String){
        movieData.value = movieName
    }

    fun saveMovie(movie: MovieEntity){
        viewModelScope.launch {
            repository.saveFavorite(movie)
        }
    }

    fun getFavoritesMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repository.getFavoritesMovies())
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }

    }

    fun deleteFavoriteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            repository.deleteFavorite(movie)
        }

    }
}