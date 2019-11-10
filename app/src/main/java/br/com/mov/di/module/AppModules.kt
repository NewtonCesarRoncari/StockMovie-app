package br.com.mov.di.module

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.mov.repository.LoginRepository
import br.com.mov.repository.UserRepository
import br.com.mov.retrofit.ConnectionRetrofit
import br.com.mov.retrofit.service.UserService
import br.com.mov.views.viewmodel.LoginViewModel
import br.com.mov.views.viewmodel.StateAppComponentsViewModel
import br.com.mov.views.viewmodel.StateUserViewModel
import br.com.mov.views.viewmodel.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    single<UserService> { ConnectionRetrofit().userService }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}

val respositoryModel = module {
    single<UserRepository> { UserRepository(get()) }
    single<LoginRepository> { LoginRepository(get()) }
}

val viewModelModule = module {
    viewModel<StateAppComponentsViewModel> { StateAppComponentsViewModel() }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<UserViewModel> { UserViewModel(get()) }
    viewModel<StateUserViewModel> { StateUserViewModel(get()) }
}