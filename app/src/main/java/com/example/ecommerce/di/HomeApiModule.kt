package com.example.ecommerce.di

import android.content.Context
import com.example.details.data.api.DetailsApi
import com.example.details.data.repository.DetailsLocalSource
import com.example.details.data.repository.DetailsRemoteSource
import com.example.details.data.repository.DetailsRepositoryImpl
import com.example.details.domain.repository.DetailsRepository
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
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiModule {

    @Singleton
    @Provides
    fun provideRepository(remoteSource: RemoteSource, localSource: LocalSource): Repository {
        return RepositoryImpl(remote = remoteSource, local = localSource)
    }

    @Singleton
    @Provides
    fun provideDetailsRepository(remoteSource: DetailsRemoteSource, localSource: DetailsLocalSource) : DetailsRepository =
        DetailsRepositoryImpl(localSource, remoteSource)

    @Singleton
    @Provides
    fun provideDetailsRemoteSource(api: DetailsApi, @ApplicationContext context: Context): DetailsRemoteSource =
        DetailsRemoteSource(api, context)

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

    @Singleton
    @Provides
    fun provideDetailsApi(retrofit: Retrofit): DetailsApi =
        retrofit.create(DetailsApi::class.java)
}