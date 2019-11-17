package br.com.mov.views.viewmodel

import androidx.lifecycle.ViewModel
import br.com.mov.models.User
import br.com.mov.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun postUser(user: User) = repository.postUser(user)

    fun postUserAuth(user: User) = repository.postUserAuth(user)

    fun findUserInDatabase() = repository.findUserInDatabase()

    fun removeUserInDatabase(user: User) = repository.removeUserInDatabase(user)

}