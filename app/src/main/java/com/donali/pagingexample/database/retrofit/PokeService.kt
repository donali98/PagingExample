package com.donali.pagingexample.database.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://pokeapi.co/api/v2/"

interface PokeService {
    @GET("pokemon")
    fun retreiveAllPokemons(
            @Query("limit") limit: String? = null,
            @Query("offset") offset: String? = null
    ): Call<PokemonResponse>

    companion object {

        fun getPokeService(): PokeService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(PokeService::class.java)
    }
}