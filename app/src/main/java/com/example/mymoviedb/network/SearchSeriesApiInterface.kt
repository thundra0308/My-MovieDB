package com.example.mymoviedb.network

import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchSeriesApiInterface {
    @GET("/3/search/tv?api_key="+ Constants.API_KEY)
    fun getSearchSeriesList(@Query("query")query: String, @Query("page")page: String): Call<SearchSeriesResponse>
}