package com.example.mymoviedb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviedb.R
import com.example.mymoviedb.models.MovieVideoResultModel
import com.example.mymoviedb.utils.Constants

class MovieVideoAdapter(private val context: Context, private val videos: List<MovieVideoResultModel>): RecyclerView.Adapter<MovieVideoAdapter.MainViewHolder>() {
    private lateinit var mListener: onItemClickListener
    inner class MainViewHolder(private val itemView: View,private val listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bindData(video: MovieVideoResultModel) {
            val container = itemView.findViewById<CardView>(R.id.video_container)
            val thumbnailView = itemView.findViewById<ImageView>(R.id.video_image_view)
            val txt = itemView.findViewById<TextView>(R.id.video_title)
            txt.text = video.name
            container.animation = AnimationUtils.loadAnimation(itemView.context,R.anim.video_list_anim)
            Glide
                .with(context)
                .load("https://img.youtube.com/vi/${video.key}/3.jpg")
                .placeholder(R.drawable.youtube_video_placeholder)
                .dontAnimate()
                .into(thumbnailView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_vitem_video,parent,false)
        return MainViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(videos[position])
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
}