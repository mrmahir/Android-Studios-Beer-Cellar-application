package com.example.mobiledevelopment_aflevering_mahir

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BeerApiService {
    @GET("/api/beers")
    suspend fun getAllBeers(): List<Beer>

    @GET("/api/Beers/{user}")
    suspend fun getUserBeers(@Path("user") user: String, @Query("orderBy") orderBy: String): List<Beer>

    @POST("/api/beers")
    suspend fun addBeer(@Body newBeer: Beer): Beer

    @PUT("/api/beers/{id}")
    suspend fun updateBeer(@Path("id") beerId: Int, @Body beer: Beer): Beer

    @DELETE("/api/beers/{id}")
    suspend fun deleteBeer(@Path("id") beerId: Int): Response<Unit>
}
