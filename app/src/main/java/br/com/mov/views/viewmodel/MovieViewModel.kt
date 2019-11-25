package br.com.mov.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.mov.models.dto.MovieRequest
import br.com.mov.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMovies() = repository.getMovies()

    fun getMoviesOrderByTitle() = repository.getMoviesOrderByTitle()

    fun getMoviesOrderByRating() = repository.getMoviesOrderByRating()

    fun checkMoviesReturned(): LiveData<List<MovieRequest>>? = repository.moviesReturned

    fun checkMoviesOrderByTitleReturned(): LiveData<List<MovieRequest>>? =
            repository.moviesOrderByTitleReturned

    fun checkMoviesOrderByRatingReturned(): LiveData<List<MovieRequest>>? =
            repository.moviesOrderByRatingReturned
}