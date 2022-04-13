package com.teck.healthycare.di

import com.google.firebase.database.FirebaseDatabase
import com.teck.healthycare.data.RepositoryImpl
import com.teck.healthycare.data.datasource.DataSource
import com.teck.healthycare.data.datasource.DataSourceImpl
import com.teck.healthycare.domain.repository.Repository
import com.teck.healthycare.presentation.main.MainFragment
import com.teck.healthycare.presentation.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Di {
    fun sourceModule() = module {
        single<FirebaseDatabase> {
            FirebaseDatabase.getInstance()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun cloudSourceModule() = module {
        single<DataSource> {
            DataSourceImpl(get())
        }
    }

    fun repositoryModule() = module {
        single<Repository> {
            RepositoryImpl(get())
        }
    }

    fun viewModelsModule() = module {
        scope<MainFragment> {
            viewModel { MainViewModel(get()) }
        }
    }
}