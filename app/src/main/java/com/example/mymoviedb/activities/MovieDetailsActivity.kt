package com.example.mymoviedb.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieCreditCastAdapter
import com.example.mymoviedb.adapters.MovieCreditCrewAdapter
import com.example.mymoviedb.adapters.MovieVideoAdapter
import com.example.mymoviedb.databinding.ActivityMovieDetailsBinding
import com.example.mymoviedb.models.*
import com.example.mymoviedb.utils.Constants
import com.flaviofaria.kenburnsview.KenBurnsView
import com.github.lzyzsd.circleprogress.ArcProgress
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class MovieDetailsActivity : AppCompatActivity() {
    private var binding: ActivityMovieDetailsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
        val movieDetails = intent.getParcelableExtra<MovieDetailsModel>(Constants.MOVIE_DETAILS)
        prepareMovieDetails(movieDetails)
        setUpRecyclerViewCreditCast(movieDetails?.credits?.cast!!)
        setUpRecyclerViewCreditCrew(movieDetails.credits.crew!!)
        setUpVideoRecyclerView(movieDetails.videos!!)
    }

    private fun setUpVideoRecyclerView(videos: MovieVideosModel) {
        val ll = findViewById<LinearLayout>(R.id.ll_video_and_trailers)
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
                val rv = binding?.rvMovieDetailVideos
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
        val ll = findViewById<LinearLayout>(R.id.ll_movie_detail_cast)
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
                val rv = binding?.rvMovieDetailCast
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
        val ll = findViewById<LinearLayout>(R.id.ll_movie_detail_crew)
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
                val rv = binding?.rvMovieDetailCrew
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

        val movieDetailPosterImage = findViewById<KenBurnsView>(R.id.movie_detail_poster_image_view)
        val movieDetailBackdropPosterCircleImageView = findViewById<CircleImageView>(R.id.movie_detail_poster_circle_image_view)
        val movieRatingBar = findViewById<ArcProgress>(R.id.movie_rating_arcbar)

        val movieDetailTitle = findViewById<TextView>(R.id.movie_detail_title)
//        var movieDetailOriginalTitleLayout = findViewById<LinearLayout>(R.id.movie_detail_original_title_layout)
//        var movieDetailOriginalLanguageLayout = findViewById<LinearLayout>(R.id.movie_detail_original_language_layout)
//        var movieDetailAdultLayout = findViewById<LinearLayout>(R.id.movie_detail_original_adult_layout)
//        var movieDetailStatusLayout = findViewById<LinearLayout>(R.id.movie_detail_original_status_layout)
//        var movieDetailRuntimeLayout = findViewById<LinearLayout>(R.id.movie_detail_original_runtime_layout)
//        var movieDetailBudgetLayout = findViewById<LinearLayout>(R.id.movie_detail_original_budget_layout)
//        var movieDetailRevenueLayout = findViewById<LinearLayout>(R.id.movie_detail_original_revenue_layout)
//        var movieDetailGenreLayout = findViewById<LinearLayout>(R.id.movie_detail_original_genre_layout)
//        var movieDetailProductionCountryLayout = findViewById<LinearLayout>(R.id.movie_detail_production_country_layout)
//        var movieDetailReleaseDateLayout = findViewById<LinearLayout>(R.id.movie_detail_release_date_layout)
//        var movieDetailHomepageLayout = findViewById<LinearLayout>(R.id.movie_detail_original_homepage_layout)
//        var movieDetailOverviewLayout = findViewById<LinearLayout>(R.id.movie_detail_overview_layout)

        var movieDetailOriginalTitle = findViewById<TextView>(R.id.movie_detail_original_title)
        var movieDetailOriginalLanguage = findViewById<TextView>(R.id.movie_detail_language)
        var movieDetailAdult = findViewById<TextView>(R.id.movie_detail_adult)
        var movieDetailStatus = findViewById<TextView>(R.id.movie_detail_status)
        var movieDetailRuntime = findViewById<TextView>(R.id.movie_detail_runtime)
        var movieDetailBudget = findViewById<TextView>(R.id.movie_detail_budget)
        var movieDetailRevenue = findViewById<TextView>(R.id.movie_detail_revenue)
        var movieDetailGenre = findViewById<TextView>(R.id.movie_detail_genre)
        var movieDetailProductionCountry = findViewById<TextView>(R.id.movie_detail_production_country)
        var movieDetailReleaseDate = findViewById<TextView>(R.id.movie_detail_release_date)
        var movieDetailHomepage = findViewById<TextView>(R.id.movie_detail_homepage)
        var movieDetailOverview = findViewById<TextView>(R.id.movie_detail_overview)
        val totalVoteCount = findViewById<TextView>(R.id.vote_count)

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
            movieDetailBudget.text = budget.toString() + " $"
        } else {
            movieDetailBudget.text = "Not Available"
        }
        if(revenue!="0") {
            movieDetailRevenue.text = revenue.toString()+" $"
        } else {
            movieDetailRevenue.text = "Not Available"
        }
        var genreText: String = ""
        for(i in genreList!!) {
            genreText += i.name
            genreText+=", "
        }
        movieDetailGenre.text = genreText
        var productionCountryText: String = ""
        for(i in productionCountries!!) {
            productionCountryText+=i.name
        }
        movieDetailProductionCountry.text = productionCountryText
        movieDetailReleaseDate.text = releaseDate.toString()
        movieDetailHomepage.text = homepage
        movieDetailOverview.text = overview
        totalVoteCount.text = totalVote.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}