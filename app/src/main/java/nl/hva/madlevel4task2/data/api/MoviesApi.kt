package nl.hva.madlevel4task2.data.api

import okhttp3.OkHttpClient
import okhttp3.Request

class MoviesApi {
    companion object{
        private const val baseUrl = "https://developer.themoviedb.org/"

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.themoviedb.org/3/search/movie?include_adult=false&language=en-US&page=1")
            .get()
            .addHeader("accept", "application/json")
            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzNWEwYTcyNDYyY2E2NGYwMDcxOWE1N2JhNDYxNTdjYyIsInN1YiI6IjY1NDM3ZGJmZTFhZDc5MDBlYTU3ZDQxYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.QizJiZ72zlcyaG6yDHEvUysEZAd6qLMczgYmbRQ7wAU")
            .build()

        val response = client.newCall(request).execute()
    }
}