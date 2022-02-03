package com.example.ecommerce.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.data.storage.entity.ResultItemEntity
import com.example.ecommerce.data.util.GsonConverter

@Database(
    entities = [
        ResultItemEntity::class,
        HotSalesEntity::class,
        BestSellerEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(GsonConverter::class)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun getStoreDao(): StoreDao
}