package br.com.mov.retrofit.service

import br.com.mov.models.dto.MovieRequestList
import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("movies")
    fun getMovies(): Call<MovieRequestList>
}