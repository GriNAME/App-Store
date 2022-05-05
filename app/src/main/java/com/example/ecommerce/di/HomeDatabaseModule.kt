package com.example.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.example.homestore_api.data.repository.LocalSource
import com.example.homestore_api.data.storage.StoreDao
import com.example.homestore_api.data.storage.StoreDatabase
import com.example.homestore_api.data.util.Constants.DATABASE_NAME
import com.example.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeDatabaseModule {

    @Singleton
    @Provides
    fun provideLocalSource(storeDao: StoreDao): LocalSource = LocalSource(storeDao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): StoreDatabase =
        Room.databaseBuilder(
            context,
            StoreDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideHomeDao(shopDatabase: StoreDatabase): StoreDao = shopDatabase.getStoreDao()

    @Singleton
    @Provides
    fun provideNavigator() = Navigator()
}