package com.example.mymoviedb.repository

import com.example.mymoviedb.network.ApiService
import com.example.mymoviedb.network.SearchMovieApiInterface
import com.example.mymoviedb.network.SearchSeriesApiInterface

class SearchResultActivityRepository() {

    val searchMovieApiService = ApiService.getInstance().create(SearchMovieApiInterface::class.java)
    fun getSearchedMovieResults(query: String, page: String) = searchMovieApiService.getSearchMoviesList(query,page)

    val searchSeriesApiService = ApiService.getInstance().create(SearchSeriesApiInterface::class.java)
    fun getSearchedSeriesResults(query: String, page: String) = searchSeriesApiService.getSearchSeriesList(query,page)

}