package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SeriesSeasonsModel(
    @SerializedName("air_date")
    var air_date: String?,
    @SerializedName("episode_count")
    var episode_count: Long,
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("season_number")
    var season_number: Long
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(air_date)
        parcel.writeLong(episode_count)
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(overview)
        parcel.writeString(poster_path)
        parcel.writeLong(season_number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesSeasonsModel> {
        override fun createFromParcel(parcel: Parcel): SeriesSeasonsModel {
            return SeriesSeasonsModel(parcel)
        }

        override fun newArray(size: Int): Array<SeriesSeasonsModel?> {
            return arrayOfNulls(size)
        }
    }
}
