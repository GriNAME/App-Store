package com.example.ecommerce.di

import android.content.Context
import com.example.cart.data.api.CartApi
import com.example.cart.data.repository.CartLocalSource
import com.example.cart.data.repository.CartRemoteSource
import com.example.cart.data.repository.CartRepositoryImpl
import com.example.cart.domain.repository.CartRepository
import com.example.homestore_api.data.api.HomeShopApi
import com.example.homestore_api.data.repository.LocalSource
import com.example.homestore_api.data.repository.RemoteSource
import com.example.homestore_api.data.repository.RepositoryImpl
import com.example.homestore_api.data.util.Constants.BASE_URL
import com.example.homestore_api.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideHomeStoreRepository(remoteSource: RemoteSource, localSource: LocalSource): Repository =
        RepositoryImpl(remote = remoteSource, local = localSource)

    @Singleton
    @Provides
    fun provideDetailsRepository(remoteSource: com.example.details_api.data.repository.DetailsRemoteSource, localSource: com.example.details_api.data.repository.DetailsLocalSource): com.example.details_api.domain.repository.DetailsRepository =
        com.example.details_api.data.repository.DetailsRepositoryImpl(remote = remoteSource, local = localSource)

    @Singleton
    @Provides
    fun provideCartRepository(remoteSource: CartRemoteSource, localSource: CartLocalSource): CartRepository =
        CartRepositoryImpl(remote = remoteSource, local = localSource)

    @Singleton
    @Provides
    fun provideHomeStoreRemoteSource(api: HomeShopApi, @ApplicationContext context: Context): RemoteSource =
        RemoteSource(api = api, context = context)

    @Singleton
    @Provides
    fun provideDetailsRemoteSource(api: com.example.details_api.data.api.DetailsApi, @ApplicationContext context: Context): com.example.details_api.data.repository.DetailsRemoteSource =
        com.example.details_api.data.repository.DetailsRemoteSource(api = api, context = context)

    @Singleton
    @Provides
    fun provideCartRemoteSource(api: CartApi, @ApplicationContext context: Context): CartRemoteSource =
        CartRemoteSource(api = api, context = context)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHomeApi(retrofit: Retrofit): HomeShopApi =
        retrofit.create(HomeShopApi::class.java)

    @Singleton
    @Provides
    fun provideDetailsApi(retrofit: Retrofit): com.example.details_api.data.api.DetailsApi =
        retrofit.create(com.example.details_api.data.api.DetailsApi::class.java)

    @Singleton
    @Provides
    fun provideCartApi(retrofit: Retrofit): CartApi =
        retrofit.create(CartApi::class.java)
}