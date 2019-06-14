package com.donali.pagingexample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.donali.pagingexample.PokemonDiffUtilCallback
import com.donali.pagingexample.R
import com.donali.pagingexample.database.retrofit.PokeResult
import kotlinx.android.synthetic.main.item_row.view.*

class PokeAdapter(): PagedListAdapter<PokeResult, PokeAdapter.ViewHolder>(PokemonDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(pokeResult: PokeResult){
            itemView.tv_poke_name.text = pokeResult.name
        }
    }
}

