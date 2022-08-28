package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieCreditsModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("cast")
    val cast: List<MovieCreditCastModel>?,
    @SerializedName("crew")
    val crew: List<MovieCreditCrewModel>?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.createTypedArrayList(MovieCreditCastModel),
        parcel.createTypedArrayList(MovieCreditCrewModel)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeTypedList(cast)
        parcel.writeTypedList(crew)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieCreditsModel> {
        override fun createFromParcel(parcel: Parcel): MovieCreditsModel {
            return MovieCreditsModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieCreditsModel?> {
            return arrayOfNulls(size)
        }
    }
}
