package com.example.ecommerce.data.util

import androidx.room.TypeConverter
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HomeStoreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverter {

    private val gson = Gson()

    @TypeConverter
    fun bestSellerToJson(bestSeller: List<BestSellerEntity>): String {
        return gson.toJson(bestSeller)
    }

    @TypeConverter
    fun bestSellerFormJson(bestSellerJson: String): List<BestSellerEntity> {
        val type = object : TypeToken<List<BestSellerEntity>>() {}.type
        return gson.fromJson(bestSellerJson, type)
    }

    @TypeConverter
    fun homeStoreToJson(homeStore: List<HomeStoreEntity>): String {
        return gson.toJson(homeStore)
    }

    @TypeConverter
    fun homeStoreFormJson(homeStoreJson: String): List<HomeStoreEntity> {
        val type = object : TypeToken<List<HomeStoreEntity>>() {}.type
        return gson.fromJson(homeStoreJson, type)
    }
}