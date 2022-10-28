package com.example.mymoviedb.activities

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.*
import com.example.mymoviedb.databinding.ActivitySeriesDetailBinding
import com.example.mymoviedb.models.*
import com.example.mymoviedb.screenstate.SeriesDetailActivityScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.SeriesDetailActivityViewModel
import com.flaviofaria.kenburnsview.KenBurnsView


class SeriesDetailActivity : BaseActivity() {

    private var binding: ActivitySeriesDetailBinding? = null
    private val viewModel: SeriesDetailActivityViewModel by lazy {
        ViewModelProvider(this).get(SeriesDetailActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding = ActivitySeriesDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val seriesId = intent.getLongExtra(Constants.MOVIE_ID,0)
        viewModel.fetchSeriesDetails(seriesId)
        viewModel.seriesDetailLiveData.observe(this) { state ->
            processSeriesDetailResponse(state)
        }
    }

    private fun processSeriesDetailResponse(state: SeriesDetailActivityScreenState<SeriesDetailsModel?>) {
        when(state) {
            is SeriesDetailActivityScreenState.Loading -> {
                binding?.pbSeriesdetailactivity?.visibility = View.VISIBLE
                binding?.clSda?.visibility = View.INVISIBLE
            }
            is SeriesDetailActivityScreenState.Success -> {
                binding?.pbSeriesdetailactivity?.visibility = View.GONE
                binding?.clSda?.visibility = View.VISIBLE
                setUpUi(state.data)
                setupAnimation()
            }
            is SeriesDetailActivityScreenState.Error -> {
                binding?.pbSeriesdetailactivity?.visibility = View.GONE
                binding?.clSda?.visibility = View.INVISIBLE
            }
        }
    }

    private fun setUpUi(data: SeriesDetailsModel?) {
        setupSeasonRecyclerView(data?.seasons!!)
        setupCastRecyclerView(data.credits?.cast!!)
        setupCrewRecyclerView(data.credits?.crew!!)
        setupVideoRecyclerView(data.videos!!)
        val bannerImage = findViewById<KenBurnsView>(R.id.keniv_sda_banner)
        val poster = findViewById<ImageView>(R.id.iv_sda_poster)
        val vote: Int = (data?.vote_average?.times(10))?.toInt()!!
        binding?.seriesRatingArcbar?.progress = vote.toFloat()
        binding?.tvSdaTvc?.text = data.vote_count.toString()
        Glide
            .with(this@SeriesDetailActivity)
            .load(Constants.IMAGE_BASE_URL+data.poster_path)
            .dontAnimate()
            .into(bannerImage)
        Glide
            .with(this@SeriesDetailActivity)
            .load(Constants.IMAGE_BASE_URL+data.backdrop_path)
            .dontAnimate()
            .into(poster)
        binding?.tvSdaTitle?.text = data.name
        binding?.tvSdaOverview?.text = data.overview
        binding?.tvSdaOriginalname?.text = data.original_name
        binding?.tvSdaNumberofseasons?.text = data.number_of_seasons.toString()
        binding?.tvSdaNumberofepisodes?.text = data.number_of_episodes.toString()
        var genres = ""
        for(i in data.genres!!) {
            genres += i.name
            if(i!=data.genres!![data.genres!!.size-1]) {
                genres = genres + ", "
            }
        }
        binding?.tvSdaGenres?.text = genres
        binding?.tvSdaFad?.text = data.first_air_date
        binding?.tvSdaLad?.text = data.last_air_date
        binding?.tvSdaAdult?.text = data.adult
        binding?.tvSdaHomepage?.text = data.homepage
        binding?.tvSdaOriginallanguages?.text = data.original_language
        var spoken = ""
        for(i in data.spoken_languages!!) {
            spoken = spoken + i.name
            if(i!=data.spoken_languages!![data.spoken_languages!!.size-1]) {
                spoken = spoken + ", "
            }
        }
        binding?.tvSdaSpokenlanguages?.text = spoken
        var languages = ""
        for(i in data.languages!!) {
            languages = languages + i
            if(i!=data.languages!![data.languages!!.size-1]) {
                languages = languages + ", "
            }
        }
        binding?.tvSdaLanguages?.text = languages
        binding?.tvSdaStatus?.text = data.status
        var createdby = ""
        for(i in data.created_by!!) {
            createdby = createdby + i.name
            if(i!=data.created_by!![data.created_by!!.size-1]) {
                createdby = createdby + ", "
            }
        }
        binding?.tvSdaCreatedby?.text = createdby
        binding?.tvSdaInproduction?.text = data.in_production.toString()
        var countries = ""
        for(i in data.production_countries!!) {
            countries = countries + i.name
            if(i!=data.production_countries!![data.production_countries!!.size-1]) {
                countries = countries + ", "
            }
        }
    }

    private fun setupSeasonRecyclerView(seasons: List<SeriesSeasonsModel>) {
        val adapter = SeriesDetailSeasonAdapter(this@SeriesDetailActivity,seasons)
        adapter.setOnClickListener(object : SeriesDetailSeasonAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

            }
        })
        val rv = binding?.rvSdaSeasons
        rv?.layoutManager = LinearLayoutManager(this@SeriesDetailActivity, LinearLayoutManager.HORIZONTAL,false)
        rv?.setHasFixedSize(true)
        rv?.adapter = adapter
    }

    private fun setupCastRecyclerView(cast: List<MovieCreditCastModel>) {
        val adapter = MovieCreditCastAdapter(this@SeriesDetailActivity,cast)
        adapter.setOnClickListener(object : MovieCreditCastAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

            }
        })
        val rv = binding?.rvSdaCast
        rv?.layoutManager = LinearLayoutManager(this@SeriesDetailActivity, LinearLayoutManager.HORIZONTAL,false)
        rv?.setHasFixedSize(true)
        rv?.adapter = adapter
    }

    private fun setupCrewRecyclerView(crew: List<MovieCreditCrewModel>) {
        val adapter = MovieCreditCrewAdapter(this@SeriesDetailActivity,crew)
        adapter.setOnClickListener(object : MovieCreditCrewAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

            }
        })
        val rv = binding?.rvSdaCrew
        rv?.layoutManager = LinearLayoutManager(this@SeriesDetailActivity, LinearLayoutManager.HORIZONTAL,false)
        rv?.setHasFixedSize(true)
        rv?.adapter = adapter
    }

    private fun setupVideoRecyclerView(videos: MovieVideosModel) {
        val ll = findViewById<LinearLayout>(R.id.ll_series_videos)
        if(videos.results?.size==0) {
            ll.visibility = View.GONE
        } else {
            ll.visibility = View.VISIBLE
            if (Constants.isNetworkAvailable(this)) {
                val adapter = MovieVideoAdapter(this@SeriesDetailActivity, videos.results!!)
                adapter.setOnClickListener(object : MovieVideoAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent =
                            Intent(this@SeriesDetailActivity, VideoPlayActivity::class.java)
                        intent.putExtra(Constants.VIDEO_DETAILS, videos)
                        intent.putExtra(Constants.CURRENT_VIDEO_POSITION, position)
                        startActivity(intent)
                    }
                })
                val rv = binding?.rvSdaVideos
                rv?.layoutManager = LinearLayoutManager(this@SeriesDetailActivity, LinearLayoutManager.VERTICAL, false)
                rv?.adapter = adapter
            }
        }
    }

    private fun setupAnimation() {
        binding?.cvSeriesOverview?.setOnClickListener {
            if(binding?.cvSdaOverview?.visibility==View.VISIBLE) {
                collapse(binding?.cvSdaOverview!!)
            } else {
                expand(binding?.cvSdaOverview!!)
            }
        }
        binding?.cvSeriesSeasons?.setOnClickListener {
            if(binding?.rvSdaSeasons?.visibility==View.VISIBLE) {
                collapse(binding?.rvSdaSeasons!!)
            } else {
                expand(binding?.rvSdaSeasons!!)
            }
        }
        binding?.cvSeriesCast?.setOnClickListener {
            if(binding?.rvSdaCast?.visibility==View.VISIBLE) {
                collapse(binding?.rvSdaCast!!)
            } else {
                expand(binding?.rvSdaCast!!)
            }
        }
        binding?.cvSeriesCrew?.setOnClickListener {
            if(binding?.rvSdaCrew?.visibility==View.VISIBLE) {
                collapse(binding?.rvSdaCrew!!)
            } else {
                expand(binding?.rvSdaCrew!!)
            }
        }
        binding?.cvSeriesAllinfo?.setOnClickListener {
            if(binding?.llSdaAllinfo?.visibility==View.VISIBLE) {
                collapse(binding?.llSdaAllinfo!!)
            } else {
                expand(binding?.llSdaAllinfo!!)
            }
        }
        binding?.cvSeriesVideos?.setOnClickListener {
            if(binding?.rvSdaVideos?.visibility==View.VISIBLE) {
                collapse(binding?.rvSdaVideos!!)
            } else {
                expand(binding?.rvSdaVideos!!)
            }
        }
    }

}