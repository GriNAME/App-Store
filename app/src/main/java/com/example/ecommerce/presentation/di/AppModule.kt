package com.example.ecommerce.presentation.di

import com.example.ecommerce.domain.repository.Repository
import com.example.ecommerce.domain.usecase.GetHomeUseCase
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