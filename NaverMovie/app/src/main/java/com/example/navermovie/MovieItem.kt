package com.example.navermovie

import com.google.gson.annotations.SerializedName



data class MovieItem(
    @SerializedName("title")
    val title : String,
    val link : String,
    val image : String,
    val subtitle : String,
    val pubDate : String,
    val director : String,
    val actor : String,
    @SerializedName("usr_rating")
    val usrRating : String
)