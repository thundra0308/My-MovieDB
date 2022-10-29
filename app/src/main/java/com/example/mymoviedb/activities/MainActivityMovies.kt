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
import com.example.mymoviedb.databinding.ActivityMainBinding
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.screenstate.MainActivityScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.MainActivityMoviesViewModel

class MainActivityMovies : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewModel: MainActivityMoviesViewModel by lazy {
        ViewModelProvider(this).get(MainActivityMoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel.popularMovieLiveData.observe(this) { state ->
            processPopularMovieListResponse(state)
        }
        val searchView = findViewById<SearchView>(R.id.seacrh_main)
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@MainActivityMovies, SearchResultActivity::class.java)
                intent.putExtra(Constants.SEARCH_QUERY,query)
                startActivity(intent)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        binding?.rdMoviemainSeries?.setOnClickListener {
            startActivity(Intent(this@MainActivityMovies,MainActivitySeries::class.java))
            finish()
        }
    }

    private fun processPopularMovieListResponse(state: MainActivityScreenState<List<ResultModel>?>) {
        when(state) {
            is MainActivityScreenState.Loading -> {
                binding?.pbMainactivity?.visibility = View.VISIBLE
                binding?.rvPopular?.visibility = View.INVISIBLE
            }
            is MainActivityScreenState.Success -> {
                binding?.pbMainactivity?.visibility = View.GONE
                binding?.rvPopular?.visibility = View.VISIBLE
                if(state.data!=null) {
                    val adapter = MovieCatagoryListAdapter(this@MainActivityMovies,state.data)
                    adapter.setOnClickListener(object : MovieCatagoryListAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val movieId = state.data[position].id
                            val intent = Intent(this@MainActivityMovies,MovieDetailsActivity::class.java)
                            intent.putExtra(Constants.MOVIE_ID,movieId)
                            startActivity(intent)
                        }
                    })
                    val rv = binding?.rvPopular
                    rv?.layoutManager = LinearLayoutManager(this@MainActivityMovies,LinearLayoutManager.HORIZONTAL,false)
                    rv?.setHasFixedSize(true)
                    rv?.adapter = adapter
                }
            }
            is MainActivityScreenState.Error -> {
                binding?.pbMainactivity?.visibility = View.GONE
                binding?.rvPopular?.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
