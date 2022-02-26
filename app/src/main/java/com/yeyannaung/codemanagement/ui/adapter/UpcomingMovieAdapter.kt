package com.yeyannaung.codemanagement.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyannaung.codemanagement.R
import com.yeyannaung.codemanagement.databinding.ItemUpcomingBinding
import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity
import com.yeyannaung.codemanagement.ui.activity.DetailActivity
import com.yeyannaung.codemanagement.util.Constant

internal class UpcomingMovieAdapter(val context: Context) :
    RecyclerView.Adapter<UpcomingMovieAdapter.UpcomingMovieViewHolder>() {

    private var upcomingMovieList: List<UpcomingMovieEntity>? = null

    internal class UpcomingMovieViewHolder(val binding: ItemUpcomingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovieViewHolder {
        return UpcomingMovieViewHolder(
            ItemUpcomingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UpcomingMovieViewHolder, position: Int) {
        upcomingMovieList?.let {
            val movie = upcomingMovieList!![position]
            val id = movie.id
            val cover = Constant.IMAGE_URL + movie.poster_path
            val title = movie.original_title
            val overview = movie.overview
            val isFavourite = movie.favourite

            holder.binding.tvTitle.text = title
            holder.binding.tvOverview.text = overview

            Glide.with(context)
                .load(cover)
                .into(holder.binding.ivCover)

            if (isFavourite) {
                holder.binding.ivFavourite.setImageResource(R.drawable.heart_red)
            } else {
                holder.binding.ivFavourite.setImageResource(R.drawable.heart)
            }

            holder.binding.cvLayout.setOnClickListener {
                navigateToDetail(id, "upcoming")
            }
        }
    }

    override fun getItemCount(): Int {
        return upcomingMovieList?.size ?: 0
    }

    fun updateUpcomingList(upcomingList: List<UpcomingMovieEntity>) {
        upcomingMovieList = upcomingList
        notifyDataSetChanged()
    }

    private fun navigateToDetail(movieId: Int, screen: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("movie_id", movieId)
        intent.putExtra("screen", screen)
        context.startActivity(intent)
    }
}