package com.yeyannaung.codemanagement.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie")
data class PopularMovieEntity(
    @ColumnInfo(name = "adult") var adult: Boolean,
    @ColumnInfo(name = "backdrop_path") var backdrop_path: String,
    @PrimaryKey @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "original_language") var original_language: String,
    @ColumnInfo(name = "original_title") var original_title: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "popularity") var popularity: Double,
    @ColumnInfo(name = "poster_path") var poster_path: String,
    @ColumnInfo(name = "release_date") var release_date: String,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "video") var video: Boolean,
    @ColumnInfo(name = "vote_average") var vote_average: Float,
    @ColumnInfo(name = "vote_count") var vote_count: Int,
    @ColumnInfo(name = "favourite") var favourite: Boolean = false
) {
    @Ignore var genre_ids: List<Int>? = null
}
