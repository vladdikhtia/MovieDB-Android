package nl.hva.madlevel4task2.repository

import android.util.Log
import kotlinx.coroutines.withTimeout
import nl.hva.madlevel4task2.data.api.ApiService
import nl.hva.madlevel4task2.data.api.MoviesApi
import nl.hva.madlevel4task2.data.api.util.Resource
import nl.hva.madlevel4task2.data.model.ListOfMovies
import nl.hva.madlevel4task2.data.model.Movie
import retrofit2.http.Query

class MovieRepository {

    private val moviesApiService: ApiService = MoviesApi.createApi()


    /**
     * suspend function that calls a suspend function from the moviesApi call
     * @return result wrapped in our own Resource sealed class
     */

    suspend fun getMovies(query : String): Resource<ListOfMovies>{
        val response = try {
            withTimeout(5_000){
                moviesApiService.getMovies(query)
            }
        } catch (e : Exception){
            Log.e("MovieRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occured")
        }
        return Resource.Success(response)
    }
}