package br.com.mov.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import br.com.mov.models.User
import br.com.mov.retrofit.callback.CallbackWithReturn
import br.com.mov.retrofit.service.UserService
import br.com.mov.views.viewmodel.UserReturned

class UserRepository(
        private val service: UserService
) {

    private var userRequest: User? = null

    val userReturn: MutableLiveData<UserReturned> =
            MutableLiveData<UserReturned>().also {
                it.value = havUser
            }

    var havUser: UserReturned = UserReturned()
        set(value) {
            field = value
            userReturn.value = value
        }

    fun clearUser() {
        havUser = UserReturned(false)
    }

    fun postUser(user: User): User? {
        val call = service.postUser(user)
        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<User> {
                    override fun whenSucess(result: User) {
                        userRequest = result
                        havUser = UserReturned(true)
                    }

                    override fun whenFailure(error: String) {
                        Log.e("Retrofit", error)
                        havUser = UserReturned(false)
                    }

                }
        ))
        return userRequest
    }
}