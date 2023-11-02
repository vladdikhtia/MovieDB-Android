package nl.hva.madlevel4task2.data.model

data class Movie(
    val id: Int,
    val backdrop_path: String,
    val poster_path: String,
    val title: String,
    val release_date: String,
    val overview: String,
    val vote_average: Double,
)