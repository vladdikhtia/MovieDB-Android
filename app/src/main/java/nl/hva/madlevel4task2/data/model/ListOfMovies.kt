package nl.hva.madlevel4task2.data.model

data class ListOfMovies(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)