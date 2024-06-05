package com.example.pagingcompose.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface BreweryApi {

    @GET("breweries")
    suspend fun getBrewery(
        @Query("page") page : Int,
        @Query("per_page") pageCount : Int
    ) : List<BreweryDto>

    companion object{
        const val BASE_URL = "https://api.openbrewerydb.org/v1/"
    }
}