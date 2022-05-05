package com.example.cart.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cart.data.storage.model.CartEntity
import com.example.cart.data.util.CartGsonConverter

@Database(
    entities = [CartEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CartGsonConverter::class)
abstract class CartDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao
}