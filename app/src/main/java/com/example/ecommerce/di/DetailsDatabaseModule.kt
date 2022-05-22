package com.example.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.example.details_api.data.repository.DetailsLocalSource
import com.example.details_api.data.storage.CartDao
import com.example.details_api.data.storage.DetailsDao
import com.example.details_api.data.storage.DetailsDatabase
import com.example.homestore_api.data.util.Constants.DATABASE_DETAILS_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailsDatabaseModule {

    @Singleton
    @Provides
    fun provideLocalSource(detailsDao: DetailsDao, cartDao: CartDao): DetailsLocalSource =
        DetailsLocalSource(detailsDao, cartDao)

    @Singleton
    @Provides
    fun provideDetailsDatabase(@ApplicationContext context: Context): DetailsDatabase =
        Room.databaseBuilder(
            context,
            DetailsDatabase::class.java,
            DATABASE_DETAILS_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDetailsDao(database: DetailsDatabase): DetailsDao =
        database.getDetailsDao()

    @Singleton
    @Provides
    fun provideCartDao(database: DetailsDatabase): CartDao =
        database.getCartDao()
}