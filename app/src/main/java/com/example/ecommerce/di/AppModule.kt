package com.example.ecommerce.di

import com.example.homestore_api.domain.repository.Repository
import com.example.homestore_api.domain.usecase.GetHomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideGetHomeUseCase(repository: Repository): GetHomeUseCase {
        return GetHomeUseCase(repository)
    }
}