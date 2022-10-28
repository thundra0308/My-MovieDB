package com.example.mymoviedb.screenstate

sealed class MainActivitySeriesScreenState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): MainActivitySeriesScreenState<T>(data)
    class Loading<T>(data: T? = null): MainActivitySeriesScreenState<T>(data)
    class Error<T>(data: T? = null, message: String): MainActivitySeriesScreenState<T>(data,message)
}