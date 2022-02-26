package com.yeyannaung.codemanagement.service

import com.yeyannaung.codemanagement.model.MovieDetail
import com.yeyannaung.codemanagement.model.PopularResponse
import com.yeyannaung.codemanagement.model.UpcomingResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/upcoming/")
    suspend fun getUpcomingMovies(
        @Query("api_key") key: String
    ) : UpcomingResponse

    @GET("3/movie/popular/")
    suspend fun getPopularMovies(
        @Query("api_key") key: String
    ) : PopularResponse

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String
    ) : MovieDetail
}