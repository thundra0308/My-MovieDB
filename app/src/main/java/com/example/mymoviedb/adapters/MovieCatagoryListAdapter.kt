package com.example.mymoviedb.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviedb.R
import com.example.mymoviedb.models.ResultModel
import com.example.mymoviedb.utils.Constants
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class MovieCatagoryListAdapter(private val context: Context,private var movieList :List<ResultModel>): RecyclerView.Adapter<MovieCatagoryListAdapter.MainViewHolder>() {
    private lateinit var mListener: onItemClickListener
    inner class MainViewHolder(private val itemView: View, private val listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bindData(movie: ResultModel) {
            val tvRating = itemView.findViewById<TextView>(R.id.tv_rating)
            val image = itemView.findViewById<ImageView>(R.id.poster_popular)
            tvRating.text = movie.rating.toString()
            Glide
                .with(context)
                .load(Constants.IMAGE_BASE_URL+movie.poster)
                .dontAnimate()
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_item_catagory,parent,false)
        val layoutParms = LinearLayout.LayoutParams((parent.width*0.36).toInt(),(550.toDp()).toPx())
        layoutParms.setMargins((15.toDp()).toPx(),(0.toDp()),(15.toDp()).toPx(),(0.toDp()))
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