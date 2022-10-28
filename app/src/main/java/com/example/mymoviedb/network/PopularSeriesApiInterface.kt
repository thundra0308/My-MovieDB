package com.example.mymoviedb.network

import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularSeriesApiInterface {
    @GET("/3/tv/popular?api_key="+ Constants.API_KEY)
    fun getPopularSeriesList(@Query("page")page: String = "1"): Call<PopularSeriesResponse>
}