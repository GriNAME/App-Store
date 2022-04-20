package com.example.homestore_api.data.util

import androidx.room.TypeConverter
import com.example.homestore_api.data.storage.entity.BestSellerEntity
import com.example.homestore_api.data.storage.entity.HotSalesEntity
import com.example.homestore_api.domain.model.BestSeller
import com.example.homestore_api.domain.model.HotSales
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
    fun homeStoreToString(hotSales: List<HotSales>): String {
        return gson.toJson(hotSales)
    }

    @TypeConverter
    fun stringToHomeStore(homeStoreJson: String): List<HotSales> {
        val type = object : TypeToken<List<HotSales>>() {}.type
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
