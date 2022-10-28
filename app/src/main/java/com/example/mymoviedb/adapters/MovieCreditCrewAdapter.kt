package com.example.mymoviedb.adapters

import android.content.Context
import android.content.res.Resources
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
import com.example.mymoviedb.models.MovieCreditCrewModel
import com.example.mymoviedb.utils.Constants
import com.flaviofaria.kenburnsview.KenBurnsView

class MovieCreditCrewAdapter(private val context: Context, private val creditList :List<MovieCreditCrewModel>): RecyclerView.Adapter<MovieCreditCrewAdapter.MainViewHolder>() {
    private lateinit var mListener: onItemClickListener
    inner class MainViewHolder(private val itemView: View, private val listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
            fun bindData(creditCrew: MovieCreditCrewModel) {
                val image = itemView.findViewById<ImageView>(R.id.movie_credit_image)
                val name = itemView.findViewById<TextView>(R.id.tv_movieCreditName)
                val characterName = itemView.findViewById<TextView>(R.id.tv_movieCreditCharacterName)
                Glide
                    .with(context)
                    .load(Constants.IMAGE_BASE_URL + creditCrew.profile_path)
                    .centerCrop()
                    .placeholder(R.drawable.ic_profileplaceholder)
                    .dontAnimate()
                    .into(image)
                name.text = "Name : \n" + creditCrew.name
                characterName.text = "Department : \n${creditCrew.department}"
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.rv_cast_credit_item, parent, false)
//        val layoutParms = LinearLayout.LayoutParams((parent.width*0.5).toInt(),(920.toDp()).toPx())
//        layoutParms.setMargins((15.toDp()).toPx(),(100.toDp()),(40.toDp()).toPx(),0)
//        view.layoutParams = layoutParms
            return MainViewHolder(view, mListener)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.bindData(creditList[position])
        }

        override fun getItemCount(): Int {
            return creditList.size
        }

        private fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()
        private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

        fun setOnClickListener(listener: onItemClickListener) {
            mListener = listener
        }
        interface onItemClickListener {
            fun onItemClick(position: Int)
        }

    }
