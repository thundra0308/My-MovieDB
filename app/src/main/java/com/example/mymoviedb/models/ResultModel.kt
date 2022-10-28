package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ResultModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String?,
    @SerializedName("poster_path")
    val poster: String?,
    @SerializedName("release_date")
    val release: String?,
    @SerializedName("vote_average")
    val rating: Double
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(poster)
        parcel.writeString(release)
        parcel.writeDouble(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ResultModel> {
        override fun createFromParcel(parcel: Parcel): ResultModel {
            return ResultModel(parcel)
        }

        override fun newArray(size: Int): Array<ResultModel?> {
            return arrayOfNulls(size)
        }
    }
}