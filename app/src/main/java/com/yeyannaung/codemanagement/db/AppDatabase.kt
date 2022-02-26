package com.yeyannaung.codemanagement.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yeyannaung.codemanagement.db.dao.MovieDao
import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity
import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity
import com.yeyannaung.codemanagement.util.DateConverter

@Database(entities = [
    PopularMovieEntity::class,
    UpcomingMovieEntity::class
], exportSchema = false, version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}