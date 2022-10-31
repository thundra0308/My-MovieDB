package com.example.mymoviedb.activities.mainactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.activities.MovieDetailsActivity
import com.example.mymoviedb.adapters.MovieCatagoryListAdapter
import com.example.mymoviedb.databinding.FragmentMainMovieBinding
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.screenstate.MainActivityScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.MainActivityMoviesViewModel

class MainMovieFragment : Fragment() {

    private var _binding: FragmentMainMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val viewModel: MainActivityMoviesViewModel = ViewModelProvider(this).get(MainActivityMoviesViewModel::class.java)
        viewModel.popularMovieLiveData.observe(viewLifecycleOwner) { state ->
            processPopularMovieListResponse(state)
        }
        return root
    }


    private fun processPopularMovieListResponse(state: MainActivityScreenState<List<ResultModel>?>) {
        when(state) {
            is MainActivityScreenState.Loading -> {
                binding.pbMainactivity.visibility = View.VISIBLE
                binding.svMma.visibility = View.VISIBLE
            }
            is MainActivityScreenState.Success -> {
                binding.pbMainactivity.visibility = View.GONE
                binding.svMma.visibility = View.VISIBLE
                if(state.data!=null) {
                    Log.e("Size of Popular Movie List", "${state.data.size}")
                    val adapter = MovieCatagoryListAdapter(requireContext(),state.data)
                    adapter.setOnClickListener(object : MovieCatagoryListAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val movieId = state.data[position].id
                            val intent = Intent(context, MovieDetailsActivity::class.java)
                            intent.putExtra(Constants.MOVIE_ID,movieId)
                            startActivity(intent)
                        }
                    })
                    val rv = binding.rvPopular
                    rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                    rv.setHasFixedSize(true)
                    rv.adapter = adapter
                }
            }
            is MainActivityScreenState.Error -> {
                binding.pbMainactivity.visibility = View.GONE
                binding.svMma.visibility = View.VISIBLE
            }
        }
    }

}