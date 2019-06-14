package com.donali.pagingexample

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.donali.pagingexample.database.retrofit.PokeResult
import com.donali.pagingexample.database.retrofit.PokeService
import com.donali.pagingexample.database.retrofit.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeDataSource : PageKeyedDataSource<String, PokeResult>() {

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, PokeResult>) {

        PokeService.getPokeService().retreiveAllPokemons().enqueue(object : Callback<PokemonResponse> {
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("CUSTOM", t.message)
            }

            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                val body = response.body()!!
                callback.onResult(body.results, body.previous, body.next)
            }

        })
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, PokeResult>) {
        val map = handleKey(params.key)

        PokeService.getPokeService().retreiveAllPokemons(map["offset"]!!, map["limit"]!!)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    Log.e("CUSTOM", t.message)
                }

                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    val body = response.body()!!
                    callback.onResult(body.results, body.next)
                }

            })
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, PokeResult>) {

        val map = handleKey(params.key)

        PokeService.getPokeService().retreiveAllPokemons(map["offset"]!!, map["limit"]!!)
            .enqueue(object : Callback<PokemonResponse> {
                override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                    Log.e("CUSTOM", t.message)
                }

                override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                    val body = response.body()!!
                    callback.onResult(body.results, body.previous)
                }

            })

    }

    private fun handleKey(key: String): MutableMap<String, String> {
        val (_, queryPart) = key.split("?")
        val queries = queryPart.split("&".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val map = mutableMapOf<String, String>()
        for (query in queries) {
            val (k, v) = query.split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            map[k] = v
        }
        return map
    }
}