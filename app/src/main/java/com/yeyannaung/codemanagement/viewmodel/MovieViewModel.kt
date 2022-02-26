package com.yeyannaung.codemanagement.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity
import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity
import com.yeyannaung.codemanagement.model.MovieDetail
import com.yeyannaung.codemanagement.model.ResultWrapper
import com.yeyannaung.codemanagement.repository.LocalRepository
import com.yeyannaung.codemanagement.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val mutableUpcomingList: MutableLiveData<ResultWrapper<List<UpcomingMovieEntity>>> =
        MutableLiveData()
    val upcomingList: LiveData<ResultWrapper<List<UpcomingMovieEntity>>> = mutableUpcomingList

    fun fetchUpcomingMovies() {
        mutableUpcomingList.value = ResultWrapper.Loading
        viewModelScope.launch {
            localRepository.fetchUpcomingMovies()
                .catch { e ->
                    mutableUpcomingList.value = ResultWrapper.Error(e)
                }.collect {
                    mutableUpcomingList.value = ResultWrapper.Success(it)
                }
        }
    }

    fun loadUpcomingMovies() {
        viewModelScope.launch {
            remoteRepository.fetchUpcomingMovies()
                .collect {
                    localRepository.insertUpcomingMovies(it.results)
                }
        }
    }

    private val mutablePopularList: MutableLiveData<ResultWrapper<List<PopularMovieEntity>>> =
        MutableLiveData()
    val popularList: LiveData<ResultWrapper<List<PopularMovieEntity>>> = mutablePopularList

    fun fetchPopularMovies() {
        mutablePopularList.value = ResultWrapper.Loading
        viewModelScope.launch {
            localRepository.fetchPopularMovies()
                .catch { e ->
                    mutablePopularList.value = ResultWrapper.Error(e)
                }.collect {
                    mutablePopularList.value = ResultWrapper.Success(it)
                }
        }
    }

    fun loadPopularMovies() {
        viewModelScope.launch {
            remoteRepository.fetchPopularMovies()
                .collect {
                    localRepository.insertPopularMovies(it.results)
                }
        }
    }

    private val mutableDetail: MutableLiveData<ResultWrapper<MovieDetail>> = MutableLiveData()
    val detail: LiveData<ResultWrapper<MovieDetail>> = mutableDetail

    fun fetchMovieDetail(movieId: Int) {
        mutableDetail.value = ResultWrapper.Loading
        viewModelScope.launch {
            remoteRepository.fetchMovieDetail(movieId)
                .catch { e ->
                    mutableDetail.value = ResultWrapper.Error(e)
                }.collect {
                    mutableDetail.value = ResultWrapper.Success(it)
                }
        }
    }
}