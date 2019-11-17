package br.com.mov.di.module

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.room.Room
import br.com.mov.database.ConnectionDatabase
import br.com.mov.database.dao.UserDAO
import br.com.mov.repository.LoginRepository
import br.com.mov.repository.MovieRepository
import br.com.mov.repository.UserRepository
import br.com.mov.retrofit.ConnectionRetrofit
import br.com.mov.retrofit.service.MovieService
import br.com.mov.retrofit.service.UserService
import br.com.mov.views.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DATABASE_NAME = "stockmovie.db"

val databaseModule = module {
    single<ConnectionDatabase> {
        Room.databaseBuilder(
                get(),
                ConnectionDatabase::class.java,
                DATABASE_NAME
        ).build()
    }
}

val serviceModule = module {
    single<UserService> { ConnectionRetrofit().userService }
    single<MovieService> { ConnectionRetrofit().movieService }
    single<UserDAO> { get<ConnectionDatabase>().userDao() }
    single<SharedPreferences> { PreferenceManager.getDefaultSharedPreferences(get()) }
}

val respositoryModel = module {
    single<UserRepository> { UserRepository(get(), get()) }
    single<MovieRepository> { MovieRepository(get()) }
    single<LoginRepository> { LoginRepository(get()) }
}

val viewModelModule = module {
    viewModel<StateAppComponentsViewModel> { StateAppComponentsViewModel() }
    viewModel<LoginViewModel> { LoginViewModel(get()) }
    viewModel<UserViewModel> { UserViewModel(get()) }
    viewModel<MovieViewModel> { MovieViewModel(get()) }
    viewModel<MovieDetailViewModel> { (movieId: Long) -> MovieDetailViewModel(movieId, get()) }
    viewModel<StateUserViewModel> { StateUserViewModel(get()) }
}