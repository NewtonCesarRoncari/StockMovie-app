package br.com.mov.views.viewmodel

import androidx.lifecycle.ViewModel
import br.com.mov.models.User
import br.com.mov.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun postUser(user: User) = repository.postUser(user)

    fun findUser() = repository.findUser()

    fun removeUser(user: User) = repository.removeUser(user)
}