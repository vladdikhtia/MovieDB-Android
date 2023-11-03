package nl.hva.madlevel4task2.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
//The value in @SerializedName should comply with the json field name.
    @SerializedName("id")
    val id: Int,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("include_adult")
    private val include_adult: Boolean = false

)
