package com.example.mymoviedb.network

import com.example.mymoviedb.models.MovieCreditsModel
import com.example.mymoviedb.models.MovieDetailsModel
import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApiInterface {
    @GET("/3/movie/{movie_id}?api_key="+ Constants.API_KEY +  "&append_to_response=videos,credits")
    fun getMovieDetailsById(@Path("movie_id")movie_id: Long): Call<MovieDetailsModel>
}