package com.donali.pagingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.donali.pagingexample.adapters.PokeAdapter
import com.donali.pagingexample.database.retrofit.PokeResult
import com.donali.pagingexample.database.retrofit.PokeService
import com.donali.pagingexample.database.viewmodels.PokemonViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private val pokeAdapter = PokeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeList()

    }

    private fun initializeList() {

        rv_poke_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pokeAdapter
        }

        val config = PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(false)
                .build()

        val liveData = initializedPagedListBuilder(config).build()
        liveData.observe(this, Observer {
            pokeAdapter.submitList(it)
        })
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<String, PokeResult> {

        val dataSourceFactory = object : DataSource.Factory<String, PokeResult>() {
            override fun create(): DataSource<String, PokeResult> {
                return PokeDataSource()
            }
        }
        return LivePagedListBuilder<String, PokeResult>(dataSourceFactory, config)
    }
}
