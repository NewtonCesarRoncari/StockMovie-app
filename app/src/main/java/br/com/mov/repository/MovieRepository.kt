package br.com.mov.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.mov.models.dto.Content
import br.com.mov.models.dto.MovieRequestList
import br.com.mov.retrofit.callback.CallbackWithReturn
import br.com.mov.retrofit.service.MovieService

class MovieRepository(
        private val service: MovieService
) {

    var moviesReturned =
            MutableLiveData<List<Content>>().apply { postValue(null) }

    fun getMovieList() {
        val call = service.getMovies()
        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<MovieRequestList> {
                    override fun whenSucess(result: MovieRequestList) {
                        Log.i("retrofit", "request sucess")
                        moviesReturned.value = result.content
                    }

                    override fun whenFailure(error: String) {
                        Log.e("retrofit", error)
                    }

                }
        ))
    }
}