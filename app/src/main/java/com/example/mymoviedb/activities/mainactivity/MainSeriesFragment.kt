package com.example.mymoviedb.activities.mainactivity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.activities.SeriesDetailActivity
import com.example.mymoviedb.adapters.MovieCatagoryListAdapter
import com.example.mymoviedb.databinding.FragmentMainMovieBinding
import com.example.mymoviedb.databinding.FragmentMainSeriesBinding
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.screenstate.MainActivitySeriesScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.MainActivitySeriesViewModel

class MainSeriesFragment : Fragment() {

    private var _binding: FragmentMainSeriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainSeriesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val viewModel: MainActivitySeriesViewModel by lazy {
            ViewModelProvider(this).get(MainActivitySeriesViewModel::class.java)
        }
        viewModel.popularSeries.observe(viewLifecycleOwner) { state ->
            processPopularSeriesResponse(state)
        }
        return root
    }

    private fun processPopularSeriesResponse(state: MainActivitySeriesScreenState<List<ResultModel>?>) {
        when(state) {
            is MainActivitySeriesScreenState.Loading -> {
                binding?.pbMainseriesactivity?.visibility = View.VISIBLE
                binding?.svSma?.visibility = View.INVISIBLE
            }
            is MainActivitySeriesScreenState.Success -> {
                binding?.pbMainseriesactivity?.visibility = View.GONE
                binding?.svSma?.visibility = View.VISIBLE
                if(state.data!=null) {
                    val adapter = MovieCatagoryListAdapter(requireContext(),state.data)
                    adapter.setOnClickListener(object : MovieCatagoryListAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val seriesId = state.data[position].id
                            val intent = Intent(context, SeriesDetailActivity::class.java)
                            intent.putExtra(Constants.MOVIE_ID,seriesId)
                            startActivity(intent)
                        }
                    })
                    val rv = binding?.rvMainseriesPopular
                    rv?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    rv?.setHasFixedSize(true)
                    rv?.adapter = adapter
                }
            }
            is MainActivitySeriesScreenState.Error -> {
                binding?.pbMainseriesactivity?.visibility = View.GONE
                binding?.svSma?.visibility = View.VISIBLE
            }
        }
    }

}