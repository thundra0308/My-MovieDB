package com.example.mymoviedb.repository

import com.example.mymoviedb.network.ApiService
import com.example.mymoviedb.network.SeriesDetailApiInterface

class SeriesDetailActivityRepository() {

    val seriesDetailApiService = ApiService.getInstance().create(SeriesDetailApiInterface::class.java)
    fun getSeriesDetails(seriesId: Long) = seriesDetailApiService.getSeriesDetails(seriesId)

}