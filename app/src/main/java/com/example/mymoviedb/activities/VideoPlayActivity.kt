package com.example.mymoviedb.activities

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubePlayerView
import com.example.mymoviedb.R
import com.example.mymoviedb.databinding.ActivityVideoPlayBinding
import com.example.mymoviedb.models.MovieVideoResultModel
import com.example.mymoviedb.models.MovieVideosModel
import com.example.mymoviedb.utils.Constants
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class VideoPlayActivity : YouTubeBaseActivity() {
    private var binding: ActivityVideoPlayBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val videos = intent.getParcelableExtra<MovieVideosModel>(Constants.VIDEO_DETAILS)
        val position: Int = intent.getIntExtra(Constants.CURRENT_VIDEO_POSITION,0)
        setUpVideoPlayView(videos?.results?.get(position)!!)
    }

    private fun setUpVideoPlayView(video: MovieVideoResultModel) {
        val videoPlayer = findViewById<YouTubePlayerView>(R.id.video_player_view)
        videoPlayer.initialize(Constants.YOUTUBE_API_KEY,object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(video.key)
                p1?.play()
            }
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@VideoPlayActivity,"Failed To Initialize",Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}