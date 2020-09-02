package com.example.navermovie

data class MovieSearchRequest(
    var lastBuildDate: String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<MovieItem>
)