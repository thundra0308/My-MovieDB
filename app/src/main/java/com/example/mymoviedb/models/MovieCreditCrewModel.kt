package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieCreditCrewModel(
    @SerializedName("credit_id")
    val credit_id: String?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("job")
    val job: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    val profile_path: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(credit_id)
        parcel.writeString(department)
        parcel.writeInt(gender)
        parcel.writeLong(id)
        parcel.writeString(job)
        parcel.writeString(name)
        parcel.writeString(profile_path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieCreditCrewModel> {
        override fun createFromParcel(parcel: Parcel): MovieCreditCrewModel {
            return MovieCreditCrewModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieCreditCrewModel?> {
            return arrayOfNulls(size)
        }
    }
}