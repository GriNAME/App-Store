package com.example.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.example.details.data.repository.DetailsLocalSource
import com.example.details.data.storage.DetailsDao
import com.example.details.data.storage.DetailsDatabase
import com.example.homestore_api.data.util.Constants.DETAILS_DATABASE_NAME
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
    fun provideLocalSource(dao: DetailsDao): DetailsLocalSource = DetailsLocalSource(dao)

    @Singleton
    @Provides
    fun provideDetailsDatabase(@ApplicationContext context: Context): DetailsDatabase =
        Room.databaseBuilder(
            context,
            DetailsDatabase::class.java,
            DETAILS_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideDetailsDao(database: DetailsDatabase): DetailsDao =
        database.getDetailsDao()
}