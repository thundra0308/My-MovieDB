package com.example.mymoviedb.screenstate

sealed class MainActivityScreenState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): MainActivityScreenState<T>(data)
    class Loading<T>(data: T? = null): MainActivityScreenState<T>(data)
    class Error<T>(data: T? = null, message: String): MainActivityScreenState<T>(data,message)
}