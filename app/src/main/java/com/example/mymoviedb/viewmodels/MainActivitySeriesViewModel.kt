package com.example.mymoviedb.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.network.PopularSeriesResponse
import com.example.mymoviedb.repository.MainActivitySeriesRepository
import com.example.mymoviedb.screenstate.MainActivitySeriesScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivitySeriesViewModel(private val repository: MainActivitySeriesRepository = MainActivitySeriesRepository()): ViewModel() {

    private var popularSeriesList: MutableLiveData<MainActivitySeriesScreenState<List<ResultModel>?>> = MutableLiveData()
    val popularSeries: LiveData<MainActivitySeriesScreenState<List<ResultModel>?>>
    get() = popularSeriesList
    init {
        fetchPopularSeries()
    }
    private fun fetchPopularSeries() {
        val client = repository.getPopularSeries("1")
        popularSeriesList.postValue(MainActivitySeriesScreenState.Loading(null))
        client.enqueue(object : Callback<PopularSeriesResponse> {
            override fun onResponse(
                call: Call<PopularSeriesResponse>,
                response: Response<PopularSeriesResponse>
            ) {
                if(response.isSuccessful) {
                    popularSeriesList.postValue(MainActivitySeriesScreenState.Success(response.body()?.popularSeries))
                } else {
                    popularSeriesList.postValue(MainActivitySeriesScreenState.Error(null,response.code().toString()))
                }
            }
            override fun onFailure(call: Call<PopularSeriesResponse>, t: Throwable) {
                Log.e("Failed Pathetically",""+t.message)
                popularSeriesList.postValue(MainActivitySeriesScreenState.Error(null,t.message.toString()))
            }
        })
    }

}