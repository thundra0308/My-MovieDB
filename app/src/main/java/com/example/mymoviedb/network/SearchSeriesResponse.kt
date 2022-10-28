package com.example.mymoviedb.network

import com.example.mymoviedb.models.ResultModel
import com.google.gson.annotations.SerializedName

data class SearchSeriesResponse(
    @SerializedName("results")
    val searchedSeriesResults: List<ResultModel>?
)