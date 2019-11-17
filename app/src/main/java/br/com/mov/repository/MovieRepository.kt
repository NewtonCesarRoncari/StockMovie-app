package br.com.mov.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.mov.models.dto.MovieFullRequest
import br.com.mov.models.dto.MovieRequest
import br.com.mov.models.dto.MovieRequestList
import br.com.mov.retrofit.callback.CallbackWithReturn
import br.com.mov.retrofit.service.MovieService

class MovieRepository(
        private val service: MovieService
) {

    var moviesReturned =
            MutableLiveData<List<MovieRequest>>().apply { postValue(null) }

    var movieReturned =
            MutableLiveData<MovieFullRequest>().apply { postValue(null) }

    fun getMovieList() {
        val call = service.getMovies()
        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<MovieRequestList> {
                    override fun whenSucess(result: MovieRequestList) {
                        Log.i("retrofit", "request sucess")
                        moviesReturned.value = result.movieRequest
                    }

                    override fun whenFailure(error: String) {
                        Log.e("retrofit", error)
                    }

                }
        ))
    }

    fun findMovieById(movieId: Long) {
        val call = service.findMovieById(movieId)
        call.enqueue(CallbackWithReturn(
                object: CallbackWithReturn.AnswerCallback<MovieFullRequest>{
                    override fun whenSucess(result: MovieFullRequest) {
                        Log.i("retrofit", "request sucess")
                        movieReturned.value = result
                    }

                    override fun whenFailure(error: String) {
                        Log.e("retrofit", error)
                    }

                }
        ))
    }
}