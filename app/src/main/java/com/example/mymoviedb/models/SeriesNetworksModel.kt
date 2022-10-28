package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SeriesNetworksModel(
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String?,
    @SerializedName("logo_path")
    var logo_path: String?,
    @SerializedName("origin_country")
    var origin_country: String?
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
        parcel.writeString(name)
        parcel.writeString(logo_path)
        parcel.writeString(origin_country)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesNetworksModel> {
        override fun createFromParcel(parcel: Parcel): SeriesNetworksModel {
            return SeriesNetworksModel(parcel)
        }

        override fun newArray(size: Int): Array<SeriesNetworksModel?> {
            return arrayOfNulls(size)
        }
    }
}
