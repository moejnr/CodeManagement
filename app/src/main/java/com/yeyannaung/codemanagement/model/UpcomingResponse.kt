package com.yeyannaung.codemanagement.model

import com.yeyannaung.codemanagement.db.entity.UpcomingMovieEntity

data class UpcomingResponse(
    var dates: Dates,
    var page: Int,
    var results: List<UpcomingMovieEntity>,
    var total_pages: Int,
    var total_results: Int
)