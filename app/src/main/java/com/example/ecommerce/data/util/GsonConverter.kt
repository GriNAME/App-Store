package com.example.ecommerce.data.util

import androidx.room.TypeConverter
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.HotSalesEntity
import com.example.ecommerce.domain.model.BestSeller
import com.example.ecommerce.domain.model.HomeStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonConverter {

    private val gson = Gson()

    @TypeConverter
    fun bestSellerToString(bestSeller: List<BestSeller>): String {
        return gson.toJson(bestSeller)
    }

    @TypeConverter
    fun stringToBestSeller(bestSellerJson: String): List<BestSeller> {
        val type = object : TypeToken<List<BestSeller>>() {}.type
        return gson.fromJson(bestSellerJson, type)
    }

    @TypeConverter
    fun homeStoreToString(homeStore: List<HomeStore>): String {
        return gson.toJson(homeStore)
    }

    @TypeConverter
    fun stringToHomeStore(homeStoreJson: String): List<HomeStore> {
        val type = object : TypeToken<List<HomeStore>>() {}.type
        return gson.fromJson(homeStoreJson, type)
    }

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
    fun homeStoreToJson(hotSales: List<HotSalesEntity>): String {
        return gson.toJson(hotSales)
    }

    @TypeConverter
    fun homeStoreFormJson(homeStoreJson: String): List<HotSalesEntity> {
        val type = object : TypeToken<List<HotSalesEntity>>() {}.type
        return gson.fromJson(homeStoreJson, type)
    }
}
