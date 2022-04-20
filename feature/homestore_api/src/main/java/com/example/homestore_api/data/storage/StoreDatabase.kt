package com.example.homestore_api.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.homestore_api.data.storage.entity.BestSellerEntity
import com.example.homestore_api.data.storage.entity.HotSalesEntity
import com.example.homestore_api.data.storage.entity.ResultItemEntity
import com.example.homestore_api.data.util.GsonConverter

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