package nl.hva.madlevel4task2.data.api

import nl.hva.madlevel4task2.data.model.ListOfMovies
import retrofit2.http.GET

interface ApiService {
    // The GET method needed to retrieve
    @GET("search/movie")
    suspend fun getMovies() :ListOfMovies
}