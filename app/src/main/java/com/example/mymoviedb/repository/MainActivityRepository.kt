package com.example.mymoviedb.repository

import com.example.mymoviedb.network.ApiService
import com.example.mymoviedb.network.PopularMovieApiInterface
import com.example.mymoviedb.network.SearchMovieApiInterface

class MainActivityRepository() {

    val popularMovieApiService = ApiService.getInstance().create(PopularMovieApiInterface::class.java)
    fun getPopularMovies(page: String) = popularMovieApiService.getPopularMoviesList(page)

}