package com.example.mymoviedb.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.network.SearchMovieResponse
import com.example.mymoviedb.network.SearchSeriesResponse
import com.example.mymoviedb.repository.SearchResultActivityRepository
import com.example.mymoviedb.screenstate.SearchResultActivityScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivityViewModel(private val repository: SearchResultActivityRepository = SearchResultActivityRepository()): ViewModel() {

    private var searchMoviesLiveData: MutableLiveData<SearchResultActivityScreenState<List<ResultModel>?>> = MutableLiveData()
    val searchMovieLiveData: LiveData<SearchResultActivityScreenState<List<ResultModel>?>>
    get() = searchMoviesLiveData
    fun fetchSearchMovieList(query: String) {
        val client = repository.getSearchedMovieResults(query,"1")
        searchMoviesLiveData.postValue(SearchResultActivityScreenState.Loading(null))
        client.enqueue(object : Callback<SearchMovieResponse> {
            override fun onResponse(
                call: Call<SearchMovieResponse>,
                response: Response<SearchMovieResponse>
            ) {
                if(response.isSuccessful) {
                    searchMoviesLiveData.postValue(SearchResultActivityScreenState.Success(response.body()?.searchedMovieResults))
                } else {
                    searchMoviesLiveData.postValue(SearchResultActivityScreenState.Error(null,response.code().toString()))
                }
            }
            override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                Log.e("Failed Pathetically",""+t.message)
                searchMoviesLiveData.postValue(SearchResultActivityScreenState.Error(null,t.message.toString()))
            }
        })
    }

    private var searchSeriesLiveData: MutableLiveData<SearchResultActivityScreenState<List<ResultModel>?>> = MutableLiveData()
    val seriesLiveData: LiveData<SearchResultActivityScreenState<List<ResultModel>?>>
        get() = searchSeriesLiveData
    fun fetchSearchSeriesList(query: String) {
        val client = repository.getSearchedSeriesResults(query,"1")
        searchSeriesLiveData.postValue(SearchResultActivityScreenState.Loading(null))
        client.enqueue(object : Callback<SearchSeriesResponse> {
            override fun onResponse(
                call: Call<SearchSeriesResponse>,
                response: Response<SearchSeriesResponse>
            ) {
                if(response.isSuccessful) {
                    searchSeriesLiveData.postValue(SearchResultActivityScreenState.Success(response.body()?.searchedSeriesResults))
                } else {
                    searchSeriesLiveData.postValue(SearchResultActivityScreenState.Error(null,response.code().toString()))
                }
            }
            override fun onFailure(call: Call<SearchSeriesResponse>, t: Throwable) {
                Log.e("Failed Pathetically",""+t.message)
                searchSeriesLiveData.postValue(SearchResultActivityScreenState.Error(null,t.message.toString()))
            }
        })
    }

}