package com.example.mymoviedb.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.SearchResultAdapter
import com.example.mymoviedb.databinding.ActivitySearchResultBinding
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.screenstate.SearchResultActivityScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.SearchResultActivityViewModel

class SearchResultActivity : AppCompatActivity() {

    private  var binding: ActivitySearchResultBinding? = null
    private val viewModel: SearchResultActivityViewModel by lazy {
        ViewModelProvider(this).get(SearchResultActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setActionBar()
        val searchQuery = intent.getStringExtra(Constants.SEARCH_QUERY)
        viewModel.fetchSearchMovieList(searchQuery!!)
        viewModel.fetchSearchSeriesList(searchQuery)
        binding?.tvSearchResult?.text = "$searchQuery"
        viewModel.searchMovieLiveData.observe(this) { state ->
            processSearchedMovieResults(state)
        }
        viewModel.seriesLiveData.observe(this) { state ->
            processSearchedSeriesResults(state)
        }
    }

    private fun processSearchedMovieResults(state: SearchResultActivityScreenState<List<ResultModel>?>) {
        when(state) {
            is SearchResultActivityScreenState.Loading -> {
                binding?.pbMoviesearchactivity?.visibility = View.VISIBLE
                binding?.tvErrormoviesearchactivity?.visibility =View.GONE
            }
            is SearchResultActivityScreenState.Success -> {
                binding?.pbMoviesearchactivity?.visibility = View.GONE
                binding?.tvErrormoviesearchactivity?.visibility =View.GONE
                if(state.data!=null && state.data.isNotEmpty()) {
                    setupMovieRecyclerView(state.data)
                } else {
                    binding?.tvErrormoviesearchactivity?.visibility =View.VISIBLE
                }
            }
            is SearchResultActivityScreenState.Error -> {
                binding?.pbMoviesearchactivity?.visibility = View.GONE
                binding?.tvErrormoviesearchactivity?.visibility =View.VISIBLE
            }
        }
    }

    private fun processSearchedSeriesResults(state: SearchResultActivityScreenState<List<ResultModel>?>) {
        when(state) {
            is SearchResultActivityScreenState.Loading -> {
                binding?.pbMoviesearchactivity?.visibility = View.VISIBLE
                binding?.tvErrormoviesearchactivity?.visibility =View.GONE
            }
            is SearchResultActivityScreenState.Success -> {
                binding?.pbMoviesearchactivity?.visibility = View.GONE
                binding?.tvErrormoviesearchactivity?.visibility =View.GONE
                if(state.data!=null && state.data.isNotEmpty()) {
                    setupSeriesRecyclerView(state.data)
                } else {
                    binding?.tvErrormoviesearchactivity?.visibility =View.VISIBLE
                }
            }
            is SearchResultActivityScreenState.Error -> {
                binding?.pbMoviesearchactivity?.visibility = View.GONE
                binding?.tvErrormoviesearchactivity?.visibility =View.VISIBLE
            }
        }
    }

    private fun setupMovieRecyclerView(movies: List<ResultModel>?) {
        val adapter = SearchResultAdapter(this@SearchResultActivity, movies!!)
        adapter.setOnClickListener(object : SearchResultAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val movieId = movies[position].id
                val intent = Intent(this@SearchResultActivity,MovieDetailsActivity::class.java)
                intent.putExtra(Constants.MOVIE_ID,movieId)
                startActivity(intent)
            }
        })
        val rv = binding?.rvMovieSearchResult
        rv?.layoutManager = LinearLayoutManager(this@SearchResultActivity,LinearLayoutManager.HORIZONTAL,false)
        rv?.setHasFixedSize(true)
        rv?.adapter = adapter
    }

    private fun setupSeriesRecyclerView(movies: List<ResultModel>?) {
        val adapter = SearchResultAdapter(this@SearchResultActivity, movies!!)
        adapter.setOnClickListener(object : SearchResultAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val seriesId = movies[position].id
                val intent = Intent(this@SearchResultActivity,SeriesDetailActivity::class.java)
                intent.putExtra(Constants.MOVIE_ID,seriesId)
                startActivity(intent)
            }
        })
        val rv = binding?.rvSeriesSearchResult
        rv?.layoutManager = LinearLayoutManager(this@SearchResultActivity,LinearLayoutManager.HORIZONTAL,false)
        rv?.setHasFixedSize(true)
        rv?.adapter = adapter
    }

    private fun setActionBar() {
        setSupportActionBar(binding?.tbSearchresult)
        if(supportActionBar!=null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_backwhite)
        }
        binding?.tbSearchresult?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}