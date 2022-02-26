package com.yeyannaung.codemanagement.repository

import com.yeyannaung.codemanagement.model.MovieDetail
import com.yeyannaung.codemanagement.model.PopularResponse
import com.yeyannaung.codemanagement.model.UpcomingResponse
import com.yeyannaung.codemanagement.service.ApiService
import com.yeyannaung.codemanagement.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun fetchUpcomingMovies(): Flow<UpcomingResponse> = flow {
        val result = apiService.getUpcomingMovies(Constant.KEY)
        emit(result)
    }.flowOn(Dispatchers.IO)

    suspend fun fetchPopularMovies(): Flow<PopularResponse> = flow {
        val result = apiService.getPopularMovies(Constant.KEY)
        emit(result)
    }.flowOn(Dispatchers.IO)

    suspend fun fetchMovieDetail(movieId: Int): Flow<MovieDetail> = flow {
        val result = apiService.getMovieDetail(movieId, Constant.KEY)
        emit(result)
    }.flowOn(Dispatchers.IO)
}