package com.yeyannaung.codemanagement.model

import com.yeyannaung.codemanagement.db.entity.PopularMovieEntity

data class PopularResponse(
    var page: Int,
    var results: List<PopularMovieEntity>,
    var total_pages: Int,
    var total_results: Int
)