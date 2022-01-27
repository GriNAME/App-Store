package com.example.ecommerce.presentation.di

import android.content.Context
import com.example.ecommerce.data.api.HomeShopApi
import com.example.ecommerce.data.repository.LocalSource
import com.example.ecommerce.data.repository.RemoteSource
import com.example.ecommerce.data.repository.RepositoryImpl
import com.example.ecommerce.data.storage.StoreDao
import com.example.ecommerce.data.util.Constants.BASE_URL
import com.example.ecommerce.domain.repository.Repository
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
    fun provideRepository(remoteSource: RemoteSource, localSource: LocalSource): Repository {
        return RepositoryImpl(remote = remoteSource, local = localSource)
    }

    @Singleton
    @Provides
    fun provideRemoteSource(api: HomeShopApi, @ApplicationContext context: Context): RemoteSource {
        return RemoteSource(api = api, context = context)
    }

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
    fun provideApi(retrofit: Retrofit): HomeShopApi {
        return retrofit.create(HomeShopApi::class.java)
    }
}