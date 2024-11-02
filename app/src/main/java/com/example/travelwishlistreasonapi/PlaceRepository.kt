package com.example.travelwishlistreasonapi

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlaceRepository {

    private val TAG = "PLACE_REPOSITORY"

    private val baseURL = "https://claraj.pythonanywhere.com/api/" // stores URL to api

    private val client: OkHttpClient =
        OkHttpClient.Builder() // OkHttpClient make requests to the API server.  Retrofit uses functions in PlaceService to convert into HTTP Requests
            .addInterceptor(AuthorizationHeaderInterceptor())  // add Auth header interceptor
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()  // creates Retrofit object
        .baseUrl(baseURL) // passes baseURL variable
        .client(client) // passes client variable
        .addConverterFactory(GsonConverterFactory.create()) // GSON converts JSON objects into Kotlin objects
        .build()  // builds the Retrofit object

    private val placeService =
        retrofit.create(PlaceService::class.java)  // creates a reference to the placeService which is a placeServiceObject

    suspend fun getAllPlaces(): List<Place> {  // defines function getALLPlaces that calls through to placeService.getAllPlaces()
        try {
            val response = placeService.getAllPlaces()

            if (response.isSuccessful) {  // connected to api server and got data back
                val places = response.body() ?: listOf()
                return places // returns nullable list of places

            } else {  // connected to server but server sent an error message
                Log.e(TAG, "Error connecting to API server ${response.errorBody()}")
                return listOf()
            }


        } catch (ex: Exception) {  // can't connect to server - network error
            Log.e(TAG, "Error connecting to API server", ex)
            return listOf()
        }
    }
}