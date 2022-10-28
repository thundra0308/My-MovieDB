package com.example.mymoviedb.screenstate

sealed class SearchResultActivityScreenState<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T): SearchResultActivityScreenState<T>(data)
    class Loading<T>(data: T? = null): SearchResultActivityScreenState<T>(data)
    class Error<T>(data: T? = null, message: String): SearchResultActivityScreenState<T>(data,message)
}