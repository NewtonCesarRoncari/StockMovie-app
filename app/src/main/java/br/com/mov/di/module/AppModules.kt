package br.com.mov.di.module

import android.content.SharedPreferences
import android.preference.PreferenceManager
import br.com.mov.repository.LoginRepository
import br.com.mov.views.viewmodel.LoginViewModel
import br.com.mov.views.viewmodel.StateAppViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val daoModule = module {
    single<LoginRepository> { LoginRepository(get()) }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}

val viewModelModule = module {
    viewModel<StateAppViewModel> { StateAppViewModel() }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
}