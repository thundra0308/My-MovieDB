package com.example.mymoviedb.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.network.PopularMovieResponse
import com.example.mymoviedb.repository.MainActivityRepository
import com.example.mymoviedb.screenstate.MainActivityScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityMoviesViewModel(private val repository: MainActivityRepository = MainActivityRepository()): ViewModel() {

    private var popularMoviesLiveData: MutableLiveData<MainActivityScreenState<List<ResultModel>?>> = MutableLiveData()
    val popularMovieLiveData: LiveData<MainActivityScreenState<List<ResultModel>?>>
    get() = popularMoviesLiveData

    init {
        fetchPopularMovieList()
    }

    private fun fetchPopularMovieList() {
        val client = repository.getPopularMovies("1")
        popularMoviesLiveData.postValue(MainActivityScreenState.Loading(null))
        client.enqueue(object : Callback<PopularMovieResponse> {
            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                if(response.isSuccessful) {
                    popularMoviesLiveData.postValue(MainActivityScreenState.Success(response.body()?.popularMovies))
                } else {
                    popularMoviesLiveData.postValue(MainActivityScreenState.Error(null,response.code().toString()))
                }
            }
            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                Log.e("Failed Pathetically",""+t.message)
                popularMoviesLiveData.postValue(MainActivityScreenState.Error(null,t.message.toString()))
            }
        })
    }

}