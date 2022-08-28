package com.example.mymoviedb.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieCatagoryListAdapter
import com.example.mymoviedb.adapters.SearchResultAdapter
import com.example.mymoviedb.databinding.ActivitySearchResultBinding
import com.example.mymoviedb.models.MovieDetailsModel
import com.example.mymoviedb.models.MoviesResultModel
import com.example.mymoviedb.network.MovieDetailsApiInterface
import com.example.mymoviedb.network.PopularMovieApiService
import com.example.mymoviedb.network.SearchMovieResponse
import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultActivity : AppCompatActivity() {
    private  var binding: ActivitySearchResultBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val searchQuery = intent.getStringExtra(Constants.SEARCH_QUERY)
        binding?.tvSearchResult?.text = "Showing Search Results For : $searchQuery"
        setActionBar()
        val searchResult = intent.getParcelableExtra<SearchMovieResponse>(Constants.SEARCH_RESULT_MAIN)?.searchMovies
        setUpRecyclerView(searchResult)
    }

    private fun getMovieDetails(movieId: Long) {
        if(Constants.isNetworkAvailable(this)) {
            val apiService = PopularMovieApiService.getInstance().create(MovieDetailsApiInterface::class.java)
            apiService.getMovieDetailsById(movieId).enqueue(object : Callback<MovieDetailsModel> {
                override fun onResponse(
                    call: Call<MovieDetailsModel>,
                    response: Response<MovieDetailsModel>
                ) {
                    if(response.isSuccessful) {
                        Log.i("Movie Details = ",""+response.body())
                        val result = response.body()
                        val intent = Intent(this@SearchResultActivity,MovieDetailsActivity::class.java)
                        intent.putExtra(Constants.MOVIE_DETAILS,result)
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                    Log.e("Failed Pathetically",""+t.message)
                }

            })
        }
    }

    private fun setUpRecyclerView(searchResult: List<MoviesResultModel>?) {
        if(Constants.isNetworkAvailable(this)) {
            val adapter = SearchResultAdapter(this@SearchResultActivity,searchResult!!)
            adapter.setOnClickListener(object : SearchResultAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    val movieid: Long = searchResult[position].id
                    getMovieDetails(movieid)
                }
            })
            val rv = binding?.rvSearchResult
            rv?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rv?.setHasFixedSize(true)
            rv?.adapter = adapter
        }
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

}