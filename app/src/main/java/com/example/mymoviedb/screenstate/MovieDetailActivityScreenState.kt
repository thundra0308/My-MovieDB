package com.example.mymoviedb.screenstate

sealed class MovieDetailActivityScreenState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): MovieDetailActivityScreenState<T>(data)
    class Loading<T>(data: T? = null): MovieDetailActivityScreenState<T>(data)
    class Error<T>(data: T? = null, message: String): MovieDetailActivityScreenState<T>(data,message)
}