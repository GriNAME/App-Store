package com.example.ecommerce.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.ecommerce.data.repository.LocalSource
import com.example.ecommerce.data.storage.StoreDao
import com.example.ecommerce.data.storage.StoreDatabase
import com.example.ecommerce.data.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideLocalSource(storeDao: StoreDao): LocalSource {
        return LocalSource(storeDao = storeDao)
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            StoreDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideHomeDao(shopDatabase: StoreDatabase): StoreDao {
        return shopDatabase.getStoreDao()
    }
}