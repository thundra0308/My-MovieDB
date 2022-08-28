package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetailSpokenLanguage(
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("name")
    val name: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(iso_639_1)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailSpokenLanguage> {
        override fun createFromParcel(parcel: Parcel): MovieDetailSpokenLanguage {
            return MovieDetailSpokenLanguage(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailSpokenLanguage?> {
            return arrayOfNulls(size)
        }
    }
}
