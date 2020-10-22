package com.example.mvvm_drinks.ui.ViewModel

import androidx.lifecycle.*
import com.example.mvvm_drinks.domain.Repository
import com.example.mvvm_drinks.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repository: Repository) : ViewModel() {

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
}