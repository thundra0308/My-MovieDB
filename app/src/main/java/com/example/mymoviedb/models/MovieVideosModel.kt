package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieVideosModel(
    @SerializedName("results")
    val results: List<MovieVideoResultModel>?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(MovieVideoResultModel)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieVideosModel> {
        override fun createFromParcel(parcel: Parcel): MovieVideosModel {
            return MovieVideosModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieVideosModel?> {
            return arrayOfNulls(size)
        }
    }
}
