package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetailGenres(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailGenres> {
        override fun createFromParcel(parcel: Parcel): MovieDetailGenres {
            return MovieDetailGenres(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailGenres?> {
            return arrayOfNulls(size)
        }
    }
}
