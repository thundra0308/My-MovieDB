package com.example.mymoviedb.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class SeriesDetailsModel(
    @SerializedName("adult")
    var adult: String?,
    @SerializedName("backdrop_path")
    var backdrop_path: String?,
    @SerializedName("first_air_date")
    var first_air_date: String?,
    @SerializedName("homepage")
    var homepage: String?,
    @SerializedName("id")
    var id: Long,
    @SerializedName("in_production")
    var in_production: Boolean,
    @SerializedName("last_air_date")
    var last_air_date: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("number_of_episodes")
    var number_of_episodes: Long,
    @SerializedName("number_of_seasons")
    var number_of_seasons: Long,
    @SerializedName("original_language")
    var original_language: String?,
    @SerializedName("original_name")
    var original_name: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("tagline")
    var tagline: String?,
    @SerializedName("vote_average")
    var vote_average: Double,
    @SerializedName("vote_count")
    var vote_count: Long,
    @SerializedName("created_by")
    var created_by: List<SeriesCreatedByModel>?,
    @SerializedName("genres")
    var genres: List<DetailGenres>?,
    @SerializedName("languages")
    var languages: List<String>?,
    @SerializedName("last_episode_to_air")
    var last_episode_to_air: SeriesLastEpisodeToAirModel?,
    @SerializedName("networks")
    var networks: List<SeriesNetworksModel>?,
    @SerializedName("origin_country")
    var origin_country: List<String>?,
    @SerializedName("production_countries")
    var production_countries: List<MovieDetailProductionCountries>?,
    @SerializedName("production_companies")
    var production_companies: List<MovieDetailProductionCompanies>?,
    @SerializedName("seasons")
    var seasons: List<SeriesSeasonsModel>?,
    @SerializedName("spoken_languages")
    var spoken_languages: List<MovieDetailSpokenLanguage>?,
    @SerializedName("videos")
    var videos: MovieVideosModel?,
    @SerializedName("credits")
    var credits: MovieCreditsModel?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readLong(),
        parcel.createTypedArrayList(SeriesCreatedByModel),
        parcel.createTypedArrayList(DetailGenres),
        parcel.createStringArrayList(),
        parcel.readParcelable(SeriesLastEpisodeToAirModel::class.java.classLoader),
        parcel.createTypedArrayList(SeriesNetworksModel),
        parcel.createStringArrayList(),
        parcel.createTypedArrayList(MovieDetailProductionCountries),
        parcel.createTypedArrayList(MovieDetailProductionCompanies),
        parcel.createTypedArrayList(SeriesSeasonsModel),
        parcel.createTypedArrayList(MovieDetailSpokenLanguage),
        parcel.readParcelable(MovieVideosModel::class.java.classLoader),
        parcel.readParcelable(MovieCreditsModel::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(adult)
        parcel.writeString(backdrop_path)
        parcel.writeString(first_air_date)
        parcel.writeString(homepage)
        parcel.writeLong(id)
        parcel.writeByte(if (in_production) 1 else 0)
        parcel.writeString(last_air_date)
        parcel.writeString(name)
        parcel.writeLong(number_of_episodes)
        parcel.writeLong(number_of_seasons)
        parcel.writeString(original_language)
        parcel.writeString(original_name)
        parcel.writeString(overview)
        parcel.writeDouble(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(status)
        parcel.writeString(tagline)
        parcel.writeDouble(vote_average)
        parcel.writeLong(vote_count)
        parcel.writeTypedList(created_by)
        parcel.writeTypedList(genres)
        parcel.writeStringList(languages)
        parcel.writeParcelable(last_episode_to_air, flags)
        parcel.writeTypedList(networks)
        parcel.writeStringList(origin_country)
        parcel.writeTypedList(production_countries)
        parcel.writeTypedList(production_companies)
        parcel.writeTypedList(seasons)
        parcel.writeTypedList(spoken_languages)
        parcel.writeParcelable(videos, flags)
        parcel.writeParcelable(credits, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SeriesDetailsModel> {
        override fun createFromParcel(parcel: Parcel): SeriesDetailsModel {
            return SeriesDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<SeriesDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}
