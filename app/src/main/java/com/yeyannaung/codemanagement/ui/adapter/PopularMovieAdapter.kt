package com.yeyannaung.codemanagement.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yeyannaung.codemanagement.R
import com.yeyannaung.codemanagement.databinding.ItemPopularBinding
import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity
import com.yeyannaung.codemanagement.ui.activity.DetailActivity
import com.yeyannaung.codemanagement.util.Constant

internal class PopularMovieAdapter(val context: Context) :
    RecyclerView.Adapter<PopularMovieAdapter.PopularMovieViewHolder>() {

    private var popularMovieList: List<PopularMovieEntity>? = null

    internal class PopularMovieViewHolder(val binding: ItemPopularBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        return PopularMovieViewHolder(
            ItemPopularBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        popularMovieList?.let {
            val movie = popularMovieList!![position]
            val id = movie.id
            val cover = Constant.IMAGE_URL + movie.poster_path
            val isFavourite = movie.favourite

            Glide.with(context)
                .load(cover)
                .into(holder.binding.ivCover)

            if (isFavourite) {
                holder.binding.ivFavourite.setImageResource(R.drawable.heart_red)
            } else {
                holder.binding.ivFavourite.setImageResource(R.drawable.heart)
            }

            holder.binding.cvLayout.setOnClickListener {
                navigateToDetail(id, "popular")
            }
        }
    }

    override fun getItemCount(): Int {
        return popularMovieList?.size ?: 0
    }

    fun updatePopularList(popularList: List<PopularMovieEntity>) {
        popularMovieList = popularList
        notifyDataSetChanged()
    }

    private fun navigateToDetail(movieId: Int, screen: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("movie_id", movieId)
        intent.putExtra("screen", screen)
        context.startActivity(intent)
    }
}