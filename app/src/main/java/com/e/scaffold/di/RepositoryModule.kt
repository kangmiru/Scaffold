package com.e.scaffold.di

import com.e.scaffold.network.SetoranSampahApi
import com.e.scaffold.persistence.SetoranSampahDao
import com.e.scaffold.repositories.SetoranSampahRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideSetoranSampahRepository(
        api: SetoranSampahApi,
        dao: SetoranSampahDao
    ): SetoranSampahRepository {
        return SetoranSampahRepository(api, dao)
    }
}