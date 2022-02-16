package com.digimaster.projectdia.utils

import com.digimaster.projectdia.model.MovieResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String = MovieRepository.API_KEY,
        @Query("page") page: Int
    ): Call<MovieResponseModel>

    @GET("movie/{movie_id}")
    fun getMovieId(
        @Query("api_key") apiKey: String = MovieRepository.API_KEY,
        @Path("movie_id") id: Int
    )
}