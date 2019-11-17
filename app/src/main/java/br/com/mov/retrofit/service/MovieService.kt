package br.com.mov.retrofit.service

import br.com.mov.models.dto.MovieFullRequest
import br.com.mov.models.dto.MovieRequestList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("movies")
    fun getMovies(): Call<MovieRequestList>

    @GET("movies/{id}")
    fun findMovieById(@Path("id") movieId: Long): Call<MovieFullRequest>
}