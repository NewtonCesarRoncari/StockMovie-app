package br.com.mov.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.mov.models.User
import br.com.mov.models.dto.UserRequest
import br.com.mov.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun postUser(user: User) = repository.postUser(user)

    fun postUserAuth(user: User) = repository.postUserAuth(user)

    fun getUser(userId: Long) = repository.getUser(userId)

    fun checkUserReturned(): LiveData<UserRequest> = repository.userFullReturned

    fun findUserInDatabase() = repository.findUserInDatabase()

    fun removeUserInDatabase(user: User) = repository.removeUserInDatabase(user)

}