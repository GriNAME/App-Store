package com.example.ecommerce.data.util

import androidx.room.TypeConverter
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
}
