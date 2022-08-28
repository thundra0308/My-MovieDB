package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieVideoResultModel(
    @SerializedName("id")
    val id: String?,
    @SerializedName("iso_639_1")
    val iso_639_1: String?,
    @SerializedName("iso_3166_1")
    val iso_3166_1: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("site")
    val site: String?,
    @SerializedName("size")
    val size: String?,
    @SerializedName("type")
    val type: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(iso_639_1)
        parcel.writeString(iso_3166_1)
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(site)
        parcel.writeString(size)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieVideoResultModel> {
        override fun createFromParcel(parcel: Parcel): MovieVideoResultModel {
            return MovieVideoResultModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieVideoResultModel?> {
            return arrayOfNulls(size)
        }
    }
}