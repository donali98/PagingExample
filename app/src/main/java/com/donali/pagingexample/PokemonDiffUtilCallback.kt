package com.donali.pagingexample

import androidx.recyclerview.widget.DiffUtil
import com.donali.pagingexample.database.retrofit.PokeResult

class PokemonDiffUtilCallback:DiffUtil.ItemCallback<PokeResult>() {
    override fun areItemsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean  = oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
        return oldItem.name == newItem.name && oldItem.url == newItem.url
    }
}