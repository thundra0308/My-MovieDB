package com.example.mymoviedb.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.MovieDetailsModel
import com.example.mymoviedb.repository.MovieDetailActivityRepository
import com.example.mymoviedb.screenstate.MovieDetailActivityScreenState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivityViewModel(private val repository: MovieDetailActivityRepository = MovieDetailActivityRepository()): ViewModel() {

    private var movieDetailsLiveData: MutableLiveData<MovieDetailActivityScreenState<MovieDetailsModel?>> = MutableLiveData()
    val movieDetailLiveData: LiveData<MovieDetailActivityScreenState<MovieDetailsModel?>>
    get() = movieDetailsLiveData

    fun fetchMovieDetails(movieId: Long) {
        val client = repository.getMovieDetails(movieId)
        movieDetailsLiveData.postValue(MovieDetailActivityScreenState.Loading(null))
        client.enqueue(object : Callback<MovieDetailsModel> {
            override fun onResponse(
                call: Call<MovieDetailsModel>,
                response: Response<MovieDetailsModel>
            ) {
                if (response.isSuccessful) {
                    movieDetailsLiveData.postValue(MovieDetailActivityScreenState.Success(response.body()))
                } else {
                    movieDetailsLiveData.postValue(MovieDetailActivityScreenState.Error(null,response.code().toString()))
                }
            }
            override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                Log.e("Failed Pathetically", "" + t.message)
                movieDetailsLiveData.postValue(MovieDetailActivityScreenState.Error(null,t.message.toString()))
            }
        })
    }

}