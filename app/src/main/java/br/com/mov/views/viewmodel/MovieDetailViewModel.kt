package br.com.mov.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.mov.models.dto.MovieFullRequest
import br.com.mov.repository.MovieRepository

class MovieDetailViewModel(
        movieId: Long,
        val repository: MovieRepository
): ViewModel(){

    fun findMovieById(movieId: Long) = repository.findMovieById(movieId)

    fun checkMovieReturned(): LiveData<MovieFullRequest> = repository.movieReturned
}