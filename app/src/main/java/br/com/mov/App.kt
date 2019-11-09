package br.com.mov

import android.app.Application
import br.com.mov.di.module.respositoryModel
import br.com.mov.di.module.serviceModule
import br.com.mov.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                    listOf(serviceModule,
                            respositoryModel,
                            viewModelModule)
            )
        }
    }
}