package com.teck.healthycare

import android.app.Application
import com.teck.healthycare.di.Di
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    Di.sourceModule(),
                    Di.cloudSourceModule(),
                    Di.repositoryModule(),
                    Di.viewModelsModule()
                )
            )
        }
    }
}