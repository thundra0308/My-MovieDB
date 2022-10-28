package com.example.mymoviedb.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviedb.R
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.utils.Constants
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class SearchResultAdapter(private val context: Context, private val movieList :List<ResultModel>): RecyclerView.Adapter<SearchResultAdapter.MainViewHolder>() {
    private lateinit var mListener: onItemClickListener
    inner class MainViewHolder(private val itemView: View,private val listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bindData(movie: ResultModel) {
            val container = itemView.findViewById<LinearLayout>(R.id.search_result_container)
            val ratingBar = itemView.findViewById<CircularProgressBar>(R.id.rating_bar_main)
            val tvRating = itemView.findViewById<TextView>(R.id.tv_rating_main)
            val image = itemView.findViewById<ImageView>(R.id.poster_popular)
            container.animation = AnimationUtils.loadAnimation(itemView.context,R.anim.video_list_anim)
            ratingBar.progress = movie.rating.toFloat()
            ratingBar.progressMax = 10.0F
            tvRating.text = movie.rating.toString()
            Glide
                .with(context)
                .load(Constants.IMAGE_BASE_URL+movie.poster)
                .centerCrop()
                .dontAnimate()
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_catagory,parent,false)
        val layoutParms = LinearLayout.LayoutParams((parent.width*0.38).toInt(),(550.toDp()).toPx())
        layoutParms.setMargins((0.toDp()).toPx(),(0.toDp()),(30.toDp()).toPx(),(0.toDp()))
        view.layoutParams = layoutParms
        return MainViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    private fun Int.toDp(): Int = (this/ Resources.getSystem().displayMetrics.density).toInt()
    private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun setOnClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

}