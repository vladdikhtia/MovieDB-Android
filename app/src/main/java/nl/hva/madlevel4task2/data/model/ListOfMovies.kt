package nl.hva.madlevel4task2.data.model

import com.google.gson.annotations.SerializedName

data class ListOfMovies(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)