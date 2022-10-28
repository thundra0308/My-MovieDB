package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SeriesLastEpisodeToAirModel(
    @SerializedName("air_date")
    var air_date: String?,
    @SerializedName("episode_number")
    var episode_number: Long,
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("runtime")
    var runtime: Long?,
    @SerializedName("season_number")
    var season_number: Long,
    @SerializedName("show_id")
    var show_id: Long,
    @SerializedName("still_path")
    var still_path: String?,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("vote_count")
    var vote_count: Long
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(air_date)
        parcel.writeLong(episode_number)
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(overview)
        parcel.writeValue(runtime)
        parcel.writeLong(season_number)
        parcel.writeLong(show_id)
        parcel.writeString(still_path)
        parcel.writeDouble(vote_average)
        parcel.writeLong(vote_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesLastEpisodeToAirModel> {
        override fun createFromParcel(parcel: Parcel): SeriesLastEpisodeToAirModel {
            return SeriesLastEpisodeToAirModel(parcel)
        }

        override fun newArray(size: Int): Array<SeriesLastEpisodeToAirModel?> {
            return arrayOfNulls(size)
        }
    }
}
