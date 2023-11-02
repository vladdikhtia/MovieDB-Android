package nl.hva.madlevel4task2.data.api

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MoviesApi {
    companion object {

        private const val baseUrl = "https://api.themoviedb.org/3/"

        fun createApi(): ApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic

            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                readTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
            }.build()

            // Create the Retrofit instance
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)

        }
    }
//            val client = OkHttpClient()
//
//        val request = Request.Builder()
//            .url("https://api.themoviedb.org/3/search/movie?include_adult=false&language=en-US&page=1")
//            .get()
//            .addHeader("accept", "application/json")
//            .addHeader("Authorization", "Bearer 35a0a72462ca64f00719a57ba46157cc")
//            .build()
//
//
//        val response = client.newCall(request).execute()



}