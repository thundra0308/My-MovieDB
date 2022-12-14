package com.example.mymoviedb.network

import android.os.Parcel
import android.os.Parcelable
import com.example.mymoviedb.models.ResultModel
import com.google.gson.annotations.SerializedName

data class SearchMovieResponse(
    @SerializedName("results")
    val searchedMovieResults: List<ResultModel>?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ResultModel)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(searchedMovieResults)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchMovieResponse> {
        override fun createFromParcel(parcel: Parcel): SearchMovieResponse {
            return SearchMovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<SearchMovieResponse?> {
            return arrayOfNulls(size)
        }
    }
}