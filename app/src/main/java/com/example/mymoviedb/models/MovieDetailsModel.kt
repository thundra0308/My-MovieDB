package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class MovieDetailsModel(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("belongs_to_collection")
    val belongs_to_collection: MovieDetailsBelongToCollection?,
    @SerializedName("budget")
    val budget: Long,
    @SerializedName("genres")
    val genres: List<DetailGenres>?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("id")
    val id: Long,
    @SerializedName("original_language")
    val original_language: String?,
    @SerializedName("original_title")
    val original_title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("production_companies")
    val production_companies: List<MovieDetailProductionCompanies>?,
    @SerializedName("production_countries")
    val production_countries: List<MovieDetailProductionCountries>?,
    @SerializedName("release_date")
    val release_date: String?,
    @SerializedName("revenue")
    val revenue: String?,
    @SerializedName("runtime")
    val runtime: Long,
    @SerializedName("spoken_language")
    val spoken_language: List<MovieDetailSpokenLanguage>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("tagline")
    val tagline: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Long,
    @SerializedName("credits")
    val credits: MovieCreditsModel?,
    @SerializedName("videos")
    val videos: MovieVideosModel?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readParcelable(MovieDetailsBelongToCollection::class.java.classLoader),
        parcel.readLong(),
        parcel.createTypedArrayList(DetailGenres),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.createTypedArrayList(MovieDetailProductionCompanies),
        parcel.createTypedArrayList(MovieDetailProductionCountries),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.createTypedArrayList(MovieDetailSpokenLanguage),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readDouble(),
        parcel.readLong(),
        parcel.readParcelable(MovieCreditsModel::class.java.classLoader),
        parcel.readParcelable(MovieVideosModel::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(backdrop_path)
        parcel.writeParcelable(belongs_to_collection, flags)
        parcel.writeLong(budget)
        parcel.writeTypedList(genres)
        parcel.writeString(homepage)
        parcel.writeLong(id)
        parcel.writeString(original_language)
        parcel.writeString(original_title)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeTypedList(production_companies)
        parcel.writeTypedList(production_countries)
        parcel.writeString(release_date)
        parcel.writeString(revenue)
        parcel.writeLong(runtime)
        parcel.writeTypedList(spoken_language)
        parcel.writeString(status)
        parcel.writeString(tagline)
        parcel.writeString(title)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(vote_average)
        parcel.writeLong(vote_count)
        parcel.writeParcelable(credits, flags)
        parcel.writeParcelable(videos, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailsModel> {
        override fun createFromParcel(parcel: Parcel): MovieDetailsModel {
            return MovieDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}
