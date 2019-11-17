package br.com.mov.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.mov.models.dto.Content
import br.com.mov.repository.MovieRepository

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    fun getMovies() = repository.getMovieList()

    fun checkMoviesReturned(): LiveData<List<Content>>? = repository.moviesReturned
}