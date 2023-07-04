package com.example.calsdown_projekatzakol.api

import com.example.calsdown_projekatzakol.model.Drinks
import com.example.calsdown_projekatzakol.utils.Constants.Companion.API_HOST
import com.example.calsdown_projekatzakol.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CocktailApi {
    @GET("randomselection.php")
    suspend fun getDrinks(
        @Header("X-RapidAPI-Key") apiKey: String = API_KEY,
        @Header("X-RapidAPI-Host") apiHost: String = API_HOST
    ) : Response<Drinks>

    @GET("search.php")
    suspend fun searchDrink(
        @Header("X-RapidAPI-Key") apiKey: String = API_KEY,
        @Header("X-RapidAPI-Host") apiHost: String = API_HOST,
        @Query("s") query: String
    ) : Response<Drinks>
}