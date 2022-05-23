package com.example.ecommerce.di

import com.example.details_api.domain.repository.DetailsRepository
import com.example.details_api.domain.usecase.CartItemUseCase
import com.example.details_api.domain.usecase.GetDetailsUseCase
import com.example.homestore_api.domain.repository.Repository
import com.example.homestore_api.domain.usecase.GetHomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @ViewModelScoped
    @Provides
    fun provideGetHomeUseCase(repository: Repository): GetHomeUseCase =
        GetHomeUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideGetDetailsUseCase(repository: DetailsRepository): GetDetailsUseCase =
        GetDetailsUseCase(repository)

    @ViewModelScoped
    @Provides
    fun provideCartItemUseCase(repository: DetailsRepository): CartItemUseCase =
        CartItemUseCase(repository)
}