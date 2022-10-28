package com.example.mymoviedb.repository

import com.example.mymoviedb.network.ApiService
import com.example.mymoviedb.network.PopularSeriesApiInterface

class MainActivitySeriesRepository {

    val popularSeriesApiService = ApiService.getInstance().create(PopularSeriesApiInterface::class.java)
    fun getPopularSeries(page: String) = popularSeriesApiService.getPopularSeriesList(page)

}