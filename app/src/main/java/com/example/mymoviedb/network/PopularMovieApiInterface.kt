package com.example.mymoviedb.network

import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET

interface PopularMovieApiInterface {
    @GET("/3/movie/popular?api_key="+Constants.API_KEY)
    fun getPopularMoviesList(): Call<PopularMovieResponse>
}