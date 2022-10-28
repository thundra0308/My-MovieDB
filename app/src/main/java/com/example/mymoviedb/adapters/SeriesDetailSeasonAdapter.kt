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
import com.example.mymoviedb.models.SeriesSeasonsModel
import com.example.mymoviedb.utils.Constants

class SeriesDetailSeasonAdapter(private val context: Context, private val seasonList :List<SeriesSeasonsModel>): RecyclerView.Adapter<SeriesDetailSeasonAdapter.MainViewHolder>() {
    private lateinit var mListener: onItemClickListener
    inner class MainViewHolder(private val itemView: View, private val listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
        fun bindData(season: SeriesSeasonsModel) {
            val image = itemView.findViewById<ImageView>(R.id.iv_seasonposter)
            val text = itemView.findViewById<TextView>(R.id.tv_seasonno)
            text.text = "Season ${season.season_number}"
            Glide
                .with(context)
                .load(Constants.IMAGE_BASE_URL+season.poster_path)
                .dontAnimate()
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_season_item,parent,false)
//        val layoutParms = LinearLayout.LayoutParams((parent.width*0.37).toInt(),(550.toDp()).toPx())
//        layoutParms.setMargins((15.toDp()).toPx(),(0.toDp()),(15.toDp()).toPx(),(0.toDp()))
//        view.layoutParams = layoutParms
        return MainViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(seasonList[position])
    }

    override fun getItemCount(): Int {
        return seasonList.size
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