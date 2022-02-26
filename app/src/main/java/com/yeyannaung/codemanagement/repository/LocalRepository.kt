package com.yeyannaung.codemanagement.repository

import com.yeyannaung.codemanagement.db.AppDatabase
import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity
import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.Executor
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val executor: Executor
) {

    fun insertPopularMovies(list: List<PopularMovieEntity>) {
        executor.execute { appDatabase.movieDao().insertPopularMovieList(list) }
    }

    fun fetchPopularMovies(): Flow<List<PopularMovieEntity>> =
        appDatabase.movieDao().fetchPopularMovieList().flowOn(Dispatchers.IO)

    fun insertUpcomingMovies(list: List<UpcomingMovieEntity>) {
        executor.execute { appDatabase.movieDao().insertUpcomingMovieList(list) }
    }

    fun fetchUpcomingMovies(): Flow<List<UpcomingMovieEntity>> =
        appDatabase.movieDao().fetchUpcomingMovieList().flowOn(Dispatchers.IO)
}