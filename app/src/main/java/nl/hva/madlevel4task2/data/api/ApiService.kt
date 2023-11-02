package nl.hva.madlevel4task2.data.api

import nl.hva.madlevel4task2.data.model.ListOfMovies
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "accept : application/json",
        "Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNWEwYTcyNDYyY2E2NGYwMDcxOWE1N2JhNDYxNTdjYyIsInN1YiI6IjY1NDM3ZGJmZTFhZDc5MDBlYTU3ZDQxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QizJiZ72zlcyaG6yDHEvUysEZAd6qLMczgYmbRQ7wAU"
    )

    // The GET method needed to retrieve
    @GET("3/search/movie")
    suspend fun getMovies(@Query("query") query: String) :ListOfMovies
}