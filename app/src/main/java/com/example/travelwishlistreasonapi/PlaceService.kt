package com.example.travelwishlistreasonapi

import retrofit2.Response
import retrofit2.http.GET

interface PlaceService {  // changed from class to interface.  Interface passed to retrofit - functions available to retrofit

    @GET("places/")  // GET method to places/ API url
    suspend fun getAllPlaces(): Response<List<Place>>  // defines getAllPlaces function returns a retrofit class response that carries a list of place objects

    // todo POST create place

    // todo update place

    // todo delete place
}