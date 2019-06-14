package com.donali.pagingexample.database.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.donali.pagingexample.database.retrofit.PokeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(app:Application):AndroidViewModel(app) {

    /*fun retreiveAllPokemons() = viewModelScope.launch(Dispatchers.IO){
        val response = PokeService.getMovieService().retreiveAllPokemons()
        if(response.isSuccessful){
            with(response){
                this.body()!!.results.forEach {
                    Log.d("CUSTOM","name : ${it.name}, url: ${it.url} ")
                }
            }
        }
    }
*/
}