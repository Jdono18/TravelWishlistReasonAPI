package com.example.travelwishlistreasonapi

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaceService {  // changed from class to interface.  Interface passed to retrofit - functions available to retrofit

    @GET("places/")  // GET method to places/ API url
    suspend fun getAllPlaces(): Response<List<Place>>  // defines getAllPlaces function returns a retrofit class response that carries a list of place objects

    @POST("places/")
    suspend fun addPlace(@Body place: Place): Response<Place>

    @PATCH("places/{id}/")  // todo update place - id of place and data about the new place - send in the body of the request
    suspend fun updatePlace(@Body place: Place, @Path("id") id: Int): Response<Place>

    @DELETE("places/{id}/")
    suspend fun deletePlace(@Path("id") id: Int): Response<String>

}