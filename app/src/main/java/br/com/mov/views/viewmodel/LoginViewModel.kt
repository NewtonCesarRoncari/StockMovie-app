package br.com.mov.views.viewmodel

import androidx.lifecycle.ViewModel
import br.com.mov.repository.LoginRepository

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    fun login() = repository.login()


    fun logout() = repository.logout()


    fun isLogged(): Boolean = repository.isLogged()
}
