package br.com.mov.retrofit.service

import br.com.mov.models.dto.MovieFullRequest
import br.com.mov.models.dto.MovieRequestList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movies?size=2000")
    fun getMovies(): Call<MovieRequestList>

    @GET("movies?sort=title&size=600")
    fun getMoviesOrderByTitle(): Call<MovieRequestList>

    @GET("movies?sort=voteAverage,desc&size=600")
    fun getMoviesOderByRating(): Call<MovieRequestList>

    @GET("movies/{id}")
    fun findMovieById(@Path("id") movieId: Long): Call<MovieFullRequest>
}