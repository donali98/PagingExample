package com.donali.pagingexample.database.retrofit

import com.squareup.moshi.Json

class PokemonResponse (
    @field:Json(name = "results")
    var results:List<PokeResult>,
    @field:Json(name = "next")
    var next:String,
    @field:Json(name = "previous")
    var previous:String

)