package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SeriesCreatedByModel(
    @SerializedName("id")
    var id: Long,
    @SerializedName("credit_id")
    var credit_id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("gender")
    var gender: Int,
    @SerializedName("profile_path")
    var profile_path: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(credit_id)
        parcel.writeString(name)
        parcel.writeInt(gender)
        parcel.writeString(profile_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesCreatedByModel> {
        override fun createFromParcel(parcel: Parcel): SeriesCreatedByModel {
            return SeriesCreatedByModel(parcel)
        }

        override fun newArray(size: Int): Array<SeriesCreatedByModel?> {
            return arrayOfNulls(size)
        }
    }
}