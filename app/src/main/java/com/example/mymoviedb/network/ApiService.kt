package com.example.mymoviedb.network

import com.example.mymoviedb.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        private var retrofit: Retrofit? = null
        fun getInstance(): Retrofit{
            if(retrofit==null) {
                retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!
        }
    }
}