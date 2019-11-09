package br.com.mov.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mov.models.User
import br.com.mov.retrofit.callback.CallbackWithReturn
import br.com.mov.retrofit.service.UserService

class UserRepository(
        private val service: UserService
) {

    fun searchUser(email: String): LiveData<ResourceLiveData<User?>> {
        val liveData = MutableLiveData<ResourceLiveData<User?>>()
        val call = service.getUser(email)

        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<User> {
                    override fun whenSucess(result: User) {
                        liveData.value = ResourceLiveData(result)
                    }

                    override fun whenFailure(error: String) {
                    }
                }))
        return liveData
    }

    fun postUser(user: User): User? {
        var userReturn: User? = null
        val call = service.postUser(user)
        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<User> {
                    override fun whenSucess(result: User) {
                        userReturn = result
                    }

                    override fun whenFailure(error: String) {
                        Log.e("Retrofit", "Deu ruim más tu é foda")
                    }

                }
        ))
        return userReturn
    }
}