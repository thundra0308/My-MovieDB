package com.example.mymoviedb.network

import android.os.Parcel
import android.os.Parcelable
import com.example.mymoviedb.models.ResultModel
import com.google.gson.annotations.SerializedName

data class PopularSeriesResponse(
    @SerializedName("results")
    val popularSeries: List<ResultModel>?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(ResultModel)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(popularSeries)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PopularSeriesResponse> {
        override fun createFromParcel(parcel: Parcel): PopularSeriesResponse {
            return PopularSeriesResponse(parcel)
        }

        override fun newArray(size: Int): Array<PopularSeriesResponse?> {
            return arrayOfNulls(size)
        }
    }
}