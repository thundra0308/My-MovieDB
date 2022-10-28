package com.example.mymoviedb.network

import com.example.mymoviedb.models.SeriesDetailsModel
import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesDetailApiInterface {
    @GET("/3/tv/{tv_id}?api_key="+ Constants.API_KEY +  "&append_to_response=videos,credits")
    fun getSeriesDetails(@Path("tv_id")tv_id: Long): Call<SeriesDetailsModel>
}