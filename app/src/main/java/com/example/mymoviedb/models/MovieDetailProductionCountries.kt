package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetailProductionCountries(
    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,
    @SerializedName("name")
    val name: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(iso_3166_1)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailProductionCountries> {
        override fun createFromParcel(parcel: Parcel): MovieDetailProductionCountries {
            return MovieDetailProductionCountries(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailProductionCountries?> {
            return arrayOfNulls(size)
        }
    }
}
