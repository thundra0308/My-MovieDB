package com.example.mymoviedb.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieCatagoryListAdapter
import com.example.mymoviedb.databinding.ActivityMainSeriesBinding
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.screenstate.MainActivityScreenState
import com.example.mymoviedb.screenstate.MainActivitySeriesScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.MainActivitySeriesViewModel

class MainActivitySeries : AppCompatActivity() {

    private var binding: ActivityMainSeriesBinding? = null
    private val viewModel: MainActivitySeriesViewModel by lazy {
        ViewModelProvider(this).get(MainActivitySeriesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainSeriesBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel.popularSeries.observe(this) { state ->
            processPopularSeriesResponse(state)
        }
        binding?.rdSeriesmainMovies?.setOnClickListener {
            startActivity(Intent(this@MainActivitySeries,MainActivityMovies::class.java))
            finish()
        }
        val searchView = findViewById<SearchView>(R.id.seacrh_mainseries)
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@MainActivitySeries, SearchResultActivity::class.java)
                intent.putExtra(Constants.SEARCH_QUERY,query)
                startActivity(intent)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun processPopularSeriesResponse(state: MainActivitySeriesScreenState<List<ResultModel>?>) {
        when(state) {
            is MainActivitySeriesScreenState.Loading -> {
                binding?.pbMainseriesactivity?.visibility = View.VISIBLE
            }
            is MainActivitySeriesScreenState.Success -> {
                binding?.pbMainseriesactivity?.visibility = View.GONE
                if(state.data!=null) {
                    val adapter = MovieCatagoryListAdapter(this@MainActivitySeries,state.data)
                    adapter.setOnClickListener(object : MovieCatagoryListAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val seriesId = state.data[position].id
                            val intent = Intent(this@MainActivitySeries,SeriesDetailActivity::class.java)
                            intent.putExtra(Constants.MOVIE_ID,seriesId)
                            startActivity(intent)
                        }
                    })
                    val rv = binding?.rvMainseriesPopular
                    rv?.layoutManager = LinearLayoutManager(this@MainActivitySeries, LinearLayoutManager.HORIZONTAL,false)
                    rv?.setHasFixedSize(true)
                    rv?.adapter = adapter
                }
            }
            is MainActivitySeriesScreenState.Error -> {
                binding?.pbMainseriesactivity?.visibility = View.GONE
            }
        }
    }

}