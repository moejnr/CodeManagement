package com.yeyannaung.codemanagement.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity
import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPopularMovieList(movieList: List<PopularMovieEntity>)

    @Query("SELECT * FROM popular_movie ORDER BY release_date DESC")
    abstract fun fetchPopularMovieList(): Flow<List<PopularMovieEntity>>

    @Query("UPDATE popular_movie SET favourite = :favourite WHERE id = :movieId")
    abstract fun updatePopularFavourite(favourite: Boolean, movieId: Int)

    @Query("SELECT favourite FROM popular_movie WHERE id = :id")
    abstract fun fetchPopularFavourite(id: Int): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertUpcomingMovieList(movieList: List<UpcomingMovieEntity>)

    @Query("SELECT * FROM upcoming_movie ORDER BY release_date DESC")
    abstract fun fetchUpcomingMovieList(): Flow<List<UpcomingMovieEntity>>

    @Query("UPDATE upcoming_movie SET favourite = :favourite WHERE id = :movieId")
    abstract fun updateUpcomingFavourite(favourite: Boolean, movieId: Int)

    @Query("SELECT favourite FROM upcoming_movie WHERE id = :id")
    abstract fun fetchUpcomingFavourite(id: Int): Flow<Boolean>
}