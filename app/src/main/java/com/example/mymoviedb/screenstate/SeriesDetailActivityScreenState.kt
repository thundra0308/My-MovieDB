package com.example.mymoviedb.screenstate

sealed class SeriesDetailActivityScreenState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): SeriesDetailActivityScreenState<T>(data)
    class Loading<T>(data: T? = null): SeriesDetailActivityScreenState<T>(data)
    class Error<T>(data: T? = null, message: String): SeriesDetailActivityScreenState<T>(data,message)
}