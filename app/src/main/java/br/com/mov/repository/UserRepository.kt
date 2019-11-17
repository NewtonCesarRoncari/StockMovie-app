package br.com.mov.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.mov.database.dao.UserDAO
import br.com.mov.models.User
import br.com.mov.models.constant.UserSituation
import br.com.mov.models.dto.UserRequest
import br.com.mov.retrofit.callback.CallbackWithReturn
import br.com.mov.retrofit.service.UserService
import br.com.mov.views.viewmodel.UserReturned
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserRepository(
        private val dao: UserDAO,
        private val service: UserService
) {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.IO + job)
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
        havUser = UserReturned(UserSituation.DEFAULT)
    }

    fun postUser(user: User): User? {
        val call = service.postUser(user)
        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<UserRequest> {
                    override fun whenSucess(result: UserRequest) {
                        userRequest = User(result)
                        insertUserInDatabase(userRequest!!)
                        havUser = UserReturned(UserSituation.RETURNED)
                    }

                    override fun whenFailure(error: String) {
                        Log.e("retrofit", error)
                        havUser = UserReturned(UserSituation.UNRETURNED)
                    }

                }
        ))
        return userRequest
    }

    fun postUserAuth(user: User): User? {
        val call = service.postUserAuth(user)
        call.enqueue(CallbackWithReturn(
                object : CallbackWithReturn.AnswerCallback<UserRequest> {
                    override fun whenSucess(result: UserRequest) {
                        userRequest = User(result)
                        insertUserInDatabase(userRequest!!)
                        havUser = UserReturned(UserSituation.RETURNED)
                    }

                    override fun whenFailure(error: String) {
                        Log.e("retrofit", error)
                        havUser = UserReturned(UserSituation.UNRETURNED)
                    }

                }
        ))
        return userRequest
    }

    fun findUserInDatabase(): LiveData<User> = dao.findUser()

    fun insertUserInDatabase(user: User): LiveData<Resource<Long>> {
        return MutableLiveData<Resource<Long>>().also { liveDate ->
            scope.launch {
                val idUser = dao.insertUser(user)
                liveDate . postValue (Resource(idUser))
            }
        }
    }

    fun removeUserInDatabase(user: User){
          scope.launch {
              dao.removeUser(user)
            }
    }
}