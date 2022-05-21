package com.example.details_api.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.details_api.data.storage.model.ProductEntity
import com.example.details_api.data.storage.model.DetailsEntity
import com.example.homestore_api.data.util.GsonConverter

@Database(
    entities = [
        DetailsEntity::class,
        ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GsonConverter::class)
abstract class DetailsDatabase : RoomDatabase() {

    abstract fun getDetailsDao(): DetailsDao
}