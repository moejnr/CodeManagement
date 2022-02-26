package com.yeyannaung.codemanagement.model

data class MovieDetail(
    var adult: Boolean,
    var backdrop_path: String,
    var belongs_to_collection: BelongsToCollection,
    var budget: Long,
    var genres: List<Genres>,
    var homepage: String,
    var id: Int,
    var imdb_id: String,
    var original_language: String,
    var original_title: String,
    var overview: String,
    var popularity: Double,
    var poster_path: String,
    var production_companies: List<ProductionCompanies>,
    var production_countries: List<ProductionCountries>,
    var release_date: String,
    var revenue: Long,
    var runtime: Int,
    var spoken_languages: List<SpokenLanguage>,
    var status: String,
    var tagline: String,
    var title: String,
    var video: Boolean,
    var vote_average: Float,
    var vote_count: Int
)
