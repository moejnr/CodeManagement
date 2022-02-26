package com.yeyannaung.codemanagement.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yeyannaung.codemanagement.databinding.ActivityMainBinding
import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity
import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity
import com.yeyannaung.codemanagement.model.ResultWrapper
import com.yeyannaung.codemanagement.ui.adapter.PopularMovieAdapter
import com.yeyannaung.codemanagement.ui.adapter.UpcomingMovieAdapter
import com.yeyannaung.codemanagement.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var popularAdapter: PopularMovieAdapter
    private lateinit var upcomingAdapter: UpcomingMovieAdapter
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configRecyclerViews()
        fetchData()
        handleUI()
    }

    private fun handleUI() {
        movieViewModel.upcomingList.observe(this) {
            when (it) {
                is ResultWrapper.Loading -> {
                }
                is ResultWrapper.Error -> {
                }
                is ResultWrapper.Success -> {
                    updateUpcomingMovies(it.data)
                }
            }
        }

        movieViewModel.popularList.observe(this) {
            when (it) {
                is ResultWrapper.Loading -> {
                }
                is ResultWrapper.Error -> {
                }
                is ResultWrapper.Success -> {
                    updatePopularMovies(it.data)
                }
            }
        }
    }

    private fun configRecyclerViews() {
        // Popular RecyclerView
        popularAdapter = PopularMovieAdapter(this)
        binding.rvPopular.itemAnimator = DefaultItemAnimator()
        binding.rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopular.adapter = popularAdapter

        // Upcoming RecyclerView
        upcomingAdapter = UpcomingMovieAdapter(this)
        binding.rvUpcoming.itemAnimator = DefaultItemAnimator()
        binding.rvUpcoming.layoutManager = LinearLayoutManager(this)
        binding.rvUpcoming.adapter = upcomingAdapter
    }

    private fun updatePopularMovies(popularList: List<PopularMovieEntity>) {
        popularAdapter.updatePopularList(popularList)
    }

    private fun updateUpcomingMovies(upcomingList: List<UpcomingMovieEntity>) {
        upcomingAdapter.updateUpcomingList(upcomingList)
    }

    private fun fetchData() {
        lifecycleScope.launch {
            // Fetch from local database
            movieViewModel.fetchUpcomingMovies()
            movieViewModel.fetchPopularMovies()

            // Load from server
            movieViewModel.loadUpcomingMovies()
            movieViewModel.loadPopularMovies()
        }
    }
}