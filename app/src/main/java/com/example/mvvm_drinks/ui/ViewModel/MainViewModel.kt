package com.example.mvvm_drinks.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mvvm_drinks.domain.Repository
import com.example.mvvm_drinks.vo.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val repository: Repository) : ViewModel() {

    val fetchMovieList = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(repository.getMovies("batman"))
        }catch (e: Exception){
            emit(Resource.Failure(e))
        }
    }
}