package com.example.mymoviedb.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieCreditCastAdapter
import com.example.mymoviedb.adapters.MovieCreditCrewAdapter
import com.example.mymoviedb.adapters.MovieVideoAdapter
import com.example.mymoviedb.databinding.ActivityMovieDetailsBinding
import com.example.mymoviedb.models.*
import com.example.mymoviedb.screenstate.MovieDetailActivityScreenState
import com.example.mymoviedb.utils.Constants
import com.example.mymoviedb.viewmodels.MovieDetailActivityViewModel
import com.flaviofaria.kenburnsview.KenBurnsView
import com.github.lzyzsd.circleprogress.ArcProgress
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class MovieDetailsActivity : BaseActivity() {

    private var binding: ActivityMovieDetailsBinding? = null
    private val viewModel: MovieDetailActivityViewModel by lazy {
        ViewModelProvider(this).get(MovieDetailActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val movieId = intent.getLongExtra(Constants.MOVIE_ID,0)
        viewModel.fetchMovieDetails(movieId)
        viewModel.movieDetailLiveData.observe(this) { state ->
            processMovieDetails(state)
        }
    }

    private fun processMovieDetails(state: MovieDetailActivityScreenState<MovieDetailsModel?>) {
        when(state) {
            is MovieDetailActivityScreenState.Loading -> {
                binding?.pbMoviedetailactivity?.visibility = View.VISIBLE
                binding?.clMda?.visibility = View.GONE
            }
            is MovieDetailActivityScreenState.Success -> {
                binding?.pbMoviedetailactivity?.visibility = View.GONE
                binding?.clMda?.visibility = View.VISIBLE
                prepareMovieDetails(state.data)
                setUpRecyclerViewCreditCast(state.data?.credits?.cast!!)
                setUpRecyclerViewCreditCrew(state.data.credits.crew!!)
                setUpVideoRecyclerView(state.data.videos!!)
                setupAnimation()
            }
            is MovieDetailActivityScreenState.Error -> {
                binding?.pbMoviedetailactivity?.visibility = View.GONE
                binding?.clMda?.visibility = View.GONE
            }
        }
    }

    private fun setUpVideoRecyclerView(videos: MovieVideosModel) {
        val ll = findViewById<LinearLayout>(R.id.ll_movies_videos)
        if(videos.results?.size==0) {
            ll.visibility = View.GONE
        } else {
            ll.visibility = View.VISIBLE
            if (Constants.isNetworkAvailable(this)) {
                val adapter = MovieVideoAdapter(this@MovieDetailsActivity, videos.results!!)
                adapter.setOnClickListener(object : MovieVideoAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent =
                            Intent(this@MovieDetailsActivity, VideoPlayActivity::class.java)
                        intent.putExtra(Constants.VIDEO_DETAILS, videos)
                        intent.putExtra(Constants.CURRENT_VIDEO_POSITION, position)
                        startActivity(intent)
                    }
                })
                val rv = binding?.rvMdaVideos
                rv?.layoutManager = LinearLayoutManager(
                    this@MovieDetailsActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                rv?.adapter = adapter
            }
        }
    }

    private fun setUpRecyclerViewCreditCast(castList: List<MovieCreditCastModel>) {
        val ll = findViewById<LinearLayout>(R.id.ll_movies_cast)
        if(castList.size==0) {
            ll.visibility = View.GONE
        } else {
            ll.visibility = View.VISIBLE
            castList.let {
                val adapter = MovieCreditCastAdapter(this@MovieDetailsActivity, castList)
                adapter.setOnClickListener(object : MovieCreditCastAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {

                    }
                })
                val rv = binding?.rvMdaCast
                rv?.layoutManager = LinearLayoutManager(
                    this@MovieDetailsActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                rv?.adapter = adapter
            }
        }
    }

    private fun setUpRecyclerViewCreditCrew(crewList: List<MovieCreditCrewModel>) {
        val ll = findViewById<LinearLayout>(R.id.ll_movies_credit)
        if(crewList.size==0) {
            ll.visibility=View.GONE
        } else {
            ll.visibility=View.VISIBLE
            crewList.let {
                val adapter = MovieCreditCrewAdapter(this@MovieDetailsActivity, crewList)
                adapter.setOnClickListener(object : MovieCreditCrewAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        TODO("Not yet implemented")
                    }
                })
                val rv = binding?.rvMdaCrew
                rv?.layoutManager = LinearLayoutManager(
                    this@MovieDetailsActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                rv?.adapter = adapter
            }
        }
    }

    private fun prepareMovieDetails(movieDetails: MovieDetailsModel?) {

        val movieDetailPosterImage = findViewById<KenBurnsView>(R.id.keniv_mda_banner)
        val movieDetailBackdropPosterCircleImageView = findViewById<ImageView>(R.id.iv_mda_poster)
        val movieRatingBar = findViewById<ArcProgress>(R.id.movies_rating_arcbar)
        val movieDetailTitle = findViewById<TextView>(R.id.tv_mda_title)
        var movieDetailOriginalTitle = findViewById<TextView>(R.id.tv_mda_originalname)
        var movieDetailOriginalLanguage = findViewById<TextView>(R.id.tv_mda_originallanguage)
        var movieDetailAdult = findViewById<TextView>(R.id.tv_mda_adult)
        var movieDetailStatus = findViewById<TextView>(R.id.tv_mda_status)
        var movieDetailRuntime = findViewById<TextView>(R.id.tv_mda_runtime)
        var movieDetailBudget = findViewById<TextView>(R.id.tv_mda_budget)
        var movieDetailRevenue = findViewById<TextView>(R.id.tv_mda_revenue)
        var movieDetailGenre = findViewById<TextView>(R.id.tv_mda_genres)
        var movieDetailProductionCountry = findViewById<TextView>(R.id.tv_mda_pcountries)
        var movieDetailReleaseDate = findViewById<TextView>(R.id.tv_mda_rd)
        var movieDetailHomepage = findViewById<TextView>(R.id.tv_mda_homepage)
        var movieDetailOverview = findViewById<TextView>(R.id.tv_mda_overview)
        val totalVoteCount = findViewById<TextView>(R.id.tv_mda_tvc)

            val rating = movieDetails?.vote_average
            val posterPath = movieDetails?.poster_path
            val backDropPath = movieDetails?.backdrop_path
            val title = movieDetails?.title
            val originalTitle = movieDetails?.original_title
            val originalLanguage = movieDetails?.original_language
            val adult = movieDetails?.adult
            val status = movieDetails?.status
            val runtime = movieDetails?.runtime
            val budget = movieDetails?.budget
            val revenue = movieDetails?.revenue
            val genreList = movieDetails?.genres
            val productionCountries = movieDetails?.production_countries
            val releaseDate = movieDetails?.release_date
            val homepage = movieDetails?.homepage
            val overview = movieDetails?.overview
            val totalVote = movieDetails?.vote_count

            Glide.with(this@MovieDetailsActivity)
                .load(Constants.IMAGE_BASE_URL + posterPath)
                .dontAnimate()
                .into(movieDetailPosterImage)
        Glide
            .with(this)
            .load(Constants.IMAGE_BASE_URL+backDropPath)
            .centerCrop()
            .dontAnimate()
            .into(movieDetailBackdropPosterCircleImageView)
        val vote: Int = (rating?.times(10))?.toInt()!!
        movieRatingBar.progress = vote.toFloat()
        movieDetailTitle.text = title
        movieDetailOriginalTitle.text = originalTitle
        movieDetailOriginalLanguage.text = originalLanguage
        if(adult==true) movieDetailAdult.text = "True"
        else movieDetailAdult.text = "False"
        movieDetailStatus.text = status
        if(runtime!=0.toLong()) {
            movieDetailRuntime.text = runtime.toString() + " Minutes"
        } else {
            movieDetailRuntime.text = "Not Available"
        }
        if(budget!=0.toLong()) {
            movieDetailBudget.text = budget.toString()
        } else {
            movieDetailBudget.text = "Not Available"
        }
        if(revenue!="0") {
            movieDetailRevenue.text = revenue.toString()
        } else {
            movieDetailRevenue.text = "Not Available"
        }
        var genreText: String = ""
        for(i in genreList!!) {
            genreText += i.name
            if(i!=genreList[genreList.size-1]) {
                genreText+=", "
            }
        }
        movieDetailGenre.text = genreText
        var productionCountryText: String = ""
        for(i in productionCountries!!) {
            productionCountryText+=i.name
            if(i!=productionCountries[productionCountries.size-1]) {
                productionCountryText+=", "
            }
        }
        movieDetailProductionCountry.text = productionCountryText
        movieDetailReleaseDate.text = releaseDate.toString()
        movieDetailHomepage.text = homepage
        movieDetailOverview.text = overview
        totalVoteCount.text = totalVote.toString()
    }

    private fun setupAnimation() {
        binding?.cvMoviesOverview?.setOnClickListener {
            if(binding?.cvMdaOverview?.visibility==View.VISIBLE) {
                collapse(binding?.cvMdaOverview!!)
            } else {
                expand(binding?.cvMdaOverview!!)
            }
        }
        binding?.cvMoviesCast?.setOnClickListener {
            if(binding?.rvMdaCast?.visibility==View.VISIBLE) {
                collapse(binding?.rvMdaCast!!)
            } else {
                expand(binding?.rvMdaCast!!)
            }
        }
        binding?.cvMoviesCrew?.setOnClickListener {
            if(binding?.rvMdaCrew?.visibility==View.VISIBLE) {
                collapse(binding?.rvMdaCrew!!)
            } else {
                expand(binding?.rvMdaCrew!!)
            }
        }
        binding?.cvMoviesAllinfo?.setOnClickListener {
            if(binding?.llMdaAllinfo?.visibility==View.VISIBLE) {
                collapse(binding?.llMdaAllinfo!!)
            } else {
                expand(binding?.llMdaAllinfo!!)
            }
        }
        binding?.cvMoviesVideos?.setOnClickListener {
            if(binding?.rvMdaVideos?.visibility==View.VISIBLE) {
                collapse(binding?.rvMdaVideos!!)
            } else {
                expand(binding?.rvMdaVideos!!)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}