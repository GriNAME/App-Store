package com.example.ecommerce.di

import android.content.Context
import androidx.room.Room
import com.example.cart.data.repository.CartLocalSource
import com.example.cart.data.storage.CartDao
import com.example.cart.data.storage.CartDatabase
import com.example.homestore_api.data.storage.StoreDao
import com.example.homestore_api.data.storage.StoreDatabase
import com.example.homestore_api.data.util.Constants.DATABASE_CART_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartDatabaseModule {

    @Singleton
    @Provides
    fun provideLocalSource(dao: CartDao): CartLocalSource = CartLocalSource(dao)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CartDatabase =
        Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            DATABASE_CART_NAME
        ).build()

    @Singleton
    @Provides
    fun provideHomeDao(cartDatabase: CartDatabase): CartDao = cartDatabase.getCartDao()
}