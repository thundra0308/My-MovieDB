package com.example.mymoviedb.network

import android.os.Parcel
import android.os.Parcelable
import com.example.mymoviedb.models.MoviesResultModel
import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("results")
    val popularMovies: List<MoviesResultModel>?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(MoviesResultModel)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(popularMovies)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularMovieResponse> {
        override fun createFromParcel(parcel: Parcel): PopularMovieResponse {
            return PopularMovieResponse(parcel)
        }

        override fun newArray(size: Int): Array<PopularMovieResponse?> {
            return arrayOfNulls(size)
        }
    }
}