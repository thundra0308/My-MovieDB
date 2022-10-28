package com.example.mymoviedb.repository

import com.example.mymoviedb.network.ApiService
import com.example.mymoviedb.network.MovieDetailsApiInterface

class MovieDetailActivityRepository() {

    val movieDetailApiService = ApiService.getInstance().create(MovieDetailsApiInterface::class.java)
    fun getMovieDetails(movieId: Long) = movieDetailApiService.getMovieDetailsById(movieId)

}