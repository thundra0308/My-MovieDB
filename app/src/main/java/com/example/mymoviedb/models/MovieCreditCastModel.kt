package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieCreditCastModel(
    @SerializedName("cast_id")
    val cast_id: Long,
    @SerializedName("character")
    val character: String?,
    @SerializedName("credit_id")
    val credit_id: String?,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String?,
    @SerializedName("order")
    val order: Long,
    @SerializedName("profile_path")
    val profile_path: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(cast_id)
        parcel.writeString(character)
        parcel.writeString(credit_id)
        parcel.writeInt(gender)
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeLong(order)
        parcel.writeString(profile_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieCreditCastModel> {
        override fun createFromParcel(parcel: Parcel): MovieCreditCastModel {
            return MovieCreditCastModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieCreditCastModel?> {
            return arrayOfNulls(size)
        }
    }
}
