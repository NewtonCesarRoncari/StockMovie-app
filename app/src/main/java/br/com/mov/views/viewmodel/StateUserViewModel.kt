package br.com.mov.views.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.mov.repository.UserRepository

class StateUserViewModel(private val repository: UserRepository) : ViewModel() {

    val userReturned: LiveData<UserReturned> = repository.userReturn
    fun clearUser() = repository.clearUser()

}

class UserReturned(
        val userReturned: Boolean = false
)