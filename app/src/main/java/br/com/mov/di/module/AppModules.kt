package br.com.mov.di.module

import br.com.mov.views.viewmodel.StateAppViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<StateAppViewModel> { StateAppViewModel() }
}