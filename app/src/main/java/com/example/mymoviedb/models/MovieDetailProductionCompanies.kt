package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetailProductionCompanies(
    @SerializedName("id")
    val id: Long,
    @SerializedName("logo_path")
    val logo_path: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("origin_country")
    val origin_country: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(logo_path)
        parcel.writeString(name)
        parcel.writeString(origin_country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailProductionCompanies> {
        override fun createFromParcel(parcel: Parcel): MovieDetailProductionCompanies {
            return MovieDetailProductionCompanies(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailProductionCompanies?> {
            return arrayOfNulls(size)
        }
    }
}
