package br.com.mov.views.viewmodel

import androidx.lifecycle.ViewModel
import br.com.mov.models.User
import br.com.mov.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    fun postUser(user: User): User? = repository.postUser(user)
}