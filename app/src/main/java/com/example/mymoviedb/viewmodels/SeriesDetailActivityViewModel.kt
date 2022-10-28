package com.example.mymoviedb.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.SeriesDetailsModel
import com.example.mymoviedb.repository.SeriesDetailActivityRepository
import com.example.mymoviedb.screenstate.SeriesDetailActivityScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesDetailActivityViewModel(private val repository: SeriesDetailActivityRepository = SeriesDetailActivityRepository()): ViewModel() {

    private var seriesDetailsLiveData: MutableLiveData<SeriesDetailActivityScreenState<SeriesDetailsModel?>> = MutableLiveData()
    val seriesDetailLiveData: LiveData<SeriesDetailActivityScreenState<SeriesDetailsModel?>>
    get() = seriesDetailsLiveData

    fun fetchSeriesDetails(seriesId: Long) {
        val client = repository.getSeriesDetails(seriesId)
        seriesDetailsLiveData.postValue(SeriesDetailActivityScreenState.Loading(null))
        client.enqueue(object : Callback<SeriesDetailsModel> {
            override fun onResponse(
                call: Call<SeriesDetailsModel>,
                response: Response<SeriesDetailsModel>
            ) {
                if (response.isSuccessful) {
                    seriesDetailsLiveData.postValue(SeriesDetailActivityScreenState.Success(response.body()))
                } else {
                    seriesDetailsLiveData.postValue(SeriesDetailActivityScreenState.Error(null,response.code().toString()))
                }
            }
            override fun onFailure(call: Call<SeriesDetailsModel>, t: Throwable) {
                Log.e("Failed Pathetically", "" + t.message)
                seriesDetailsLiveData.postValue(SeriesDetailActivityScreenState.Error(null,t.message.toString()))
            }
        })
    }

}