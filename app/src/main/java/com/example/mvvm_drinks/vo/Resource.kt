package com.example.mvvm_drinks.vo

import java.lang.Exception

// RESOURCE TO HANDLE STATE
sealed class Resource<out T> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Failure<out T>(val exception: Exception): Resource<T>()
    class Loading<out T> : Resource<T>()
}