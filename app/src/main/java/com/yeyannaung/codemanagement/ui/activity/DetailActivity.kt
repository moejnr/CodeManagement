package com.yeyannaung.codemanagement.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.yeyannaung.codemanagement.databinding.ActivityDetailBinding
import com.yeyannaung.codemanagement.model.MovieDetail
import com.yeyannaung.codemanagement.model.ResultWrapper
import com.yeyannaung.codemanagement.util.Constant
import com.yeyannaung.codemanagement.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val movieId = intent.getIntExtra("movie_id", 0)
        val screen = intent.getStringExtra("screen")

        fetchDetail(movieId)
        handleUI()
    }

    private fun handleUI() {
        movieViewModel.detail.observe(this) {
            when (it) {
                is ResultWrapper.Loading -> {
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Error -> {
                    Toast.makeText(this, it.throwable.message, Toast.LENGTH_SHORT).show()
                }
                is ResultWrapper.Success -> {
                    updateUI(it.data)
                }
            }
        }
    }

    private fun updateUI(movieDetail: MovieDetail) {
        val id = movieDetail.id
        val title = movieDetail.title
        val cover = Constant.IMAGE_URL + movieDetail.poster_path
        val description = movieDetail.overview

        binding.tvTitle.text = title
        binding.tvDescription.text = description

        Glide.with(this)
            .load(cover)
            .into(binding.ivCover)
    }

    private fun fetchDetail(movieId: Int) {
        lifecycleScope.launch {
            movieViewModel.fetchMovieDetail(movieId)
        }
    }
}