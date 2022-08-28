package com.example.mymoviedb.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {
    fun isNetworkAvailable(context: Context) : Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)
            return when {
                activeNetwork!!.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
    const val BASE_URL: String = "https://api.themoviedb.org/"
    const val API_KEY: String = "c1b5b7e9d84692401f13e8bca4162c49"
    const val IMAGE_BASE_URL: String = "https://image.tmdb.org/t/p/w500"
    const val YOUTUBE_API_KEY: String = "AIzaSyDvENg2aAzwczL77RNcMmpSEJPXG8CA8Lc"
    const val YOUTUBE_THUMBNAIL: String = ""


    const val SEARCH_RESULT_MAIN: String = "search_result_main"
    const val SEARCH_QUERY: String = "search query"
    const val MOVIE_DETAILS: String = "movie_details"
    const val VIDEO_DETAILS: String = "video_details"
    const val CURRENT_VIDEO_POSITION: String = "current_video_position"

}