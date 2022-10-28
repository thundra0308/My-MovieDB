package com.example.mymoviedb.network

import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularMovieApiInterface {
    @GET("/3/movie/popular?api_key="+Constants.API_KEY)
    fun getPopularMoviesList(@Query("page")page: String = "1"): Call<PopularMovieResponse>
}