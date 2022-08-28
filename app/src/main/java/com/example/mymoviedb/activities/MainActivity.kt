package com.example.mymoviedb.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieCatagoryListAdapter
import com.example.mymoviedb.databinding.ActivityMainBinding
import com.example.mymoviedb.models.MovieDetailsModel
import com.example.mymoviedb.network.*
import com.example.mymoviedb.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getPopularMovieList()
        val searchView = findViewById<SearchView>(R.id.seacrh_main)
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getSearchedMovieResults(query)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun getMovieDetails(movieId: Long) {
        if(Constants.isNetworkAvailable(this)) {
            val apiService = PopularMovieApiService.getInstance().create(MovieDetailsApiInterface::class.java)
            apiService.getMovieDetailsById(movieId).enqueue(object : Callback<MovieDetailsModel> {
                override fun onResponse(
                    call: Call<MovieDetailsModel>,
                    response: Response<MovieDetailsModel>
                ) {
                    if (response.isSuccessful) {
                        Log.i("Movie Details = ", "" + response.body())
                        var result = response.body()
                        result.let {
                            val intent = Intent(this@MainActivity, MovieDetailsActivity::class.java)
                            intent.putExtra(Constants.MOVIE_DETAILS, result)
                            startActivity(intent)
                        }
                    }
                }

                override fun onFailure(call: Call<MovieDetailsModel>, t: Throwable) {
                    Log.e("Failed Pathetically", "" + t.message)
                }

            })
        }
    }

    private fun getSearchedMovieResults(query: String?) {
        if(Constants.isNetworkAvailable(this)) {
            val apiService = PopularMovieApiService.getInstance().create(SearchMovieInterface::class.java)
            apiService.getSearchMoviesList(query!!).enqueue(object : Callback<SearchMovieResponse>{
                override fun onResponse(
                    call: Call<SearchMovieResponse>,
                    response: Response<SearchMovieResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.i("Search Movie List = ",""+response.body())
                        val result = response.body()
                        result.let {
                            val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
                            intent.putExtra(Constants.SEARCH_RESULT_MAIN, result)
                            intent.putExtra(Constants.SEARCH_QUERY,query)
                            startActivity(intent)
                        }
                    } else {
                        Log.e("Failed",response.body().toString())
                    }
                }
                override fun onFailure(call: Call<SearchMovieResponse>, t: Throwable) {
                    Log.e("Failed Pathetically",""+t.message)
                }

            })
        }
    }

    private fun getPopularMovieList() {
        if(Constants.isNetworkAvailable(this)) {
            val apiService = PopularMovieApiService.getInstance().create(PopularMovieApiInterface::class.java)
            apiService.getPopularMoviesList().enqueue(object : Callback<PopularMovieResponse>{
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.i("Popular Movie List = ",""+response.body())
                        val result = response.body()?.popularMovies
                        result?.let {
                            val adapter = MovieCatagoryListAdapter(this@MainActivity,result)
                            adapter.setOnClickListener(object : MovieCatagoryListAdapter.onItemClickListener{
                                override fun onItemClick(position: Int) {
                                    val movieid: Long = result[position].id
                                    getMovieDetails(movieid)
                                }
                            })
                            val rv = binding?.rvPopular
                            rv?.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                            rv?.setHasFixedSize(true)
                            rv?.adapter = adapter
                        }
                    }
                }
                override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                    Log.e("Failed Pathetically",""+t.message)
                }
            })
        }
    }

    private fun getTrendingMovieList() {
        if(Constants.isNetworkAvailable(this)) {
            val apiService = PopularMovieApiService.getInstance().create(PopularMovieApiInterface::class.java)
            apiService.getPopularMoviesList().enqueue(object : Callback<PopularMovieResponse>{
                override fun onResponse(
                    call: Call<PopularMovieResponse>,
                    response: Response<PopularMovieResponse>
                ) {
                    if(response.isSuccessful) {
                        Log.i("Popular Movie List = ",""+response.body())
                        val result = response.body()?.popularMovies
                        result?.let {
                            val adapter = MovieCatagoryListAdapter(this@MainActivity,result)
                            adapter.setOnClickListener(object : MovieCatagoryListAdapter.onItemClickListener{
                                override fun onItemClick(position: Int) {
                                    val movieid: Long = result[position].id
                                    getMovieDetails(movieid)
                                }
                            })
                            val rv = binding?.rvPopular
                            rv?.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                            rv?.setHasFixedSize(true)
                            rv?.adapter = adapter
                        }
                    }
                }
                override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                    Log.e("Failed Pathetically",""+t.message)
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}
