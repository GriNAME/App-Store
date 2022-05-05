package com.example.cart.data.util

import androidx.room.TypeConverter
import com.example.cart.data.storage.model.BasketEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartGsonConverter {

    private val gson = Gson()

    @TypeConverter
    fun basketToJson(baskets: List<BasketEntity>): String =
        gson.toJson(baskets)

    @TypeConverter
    fun basketFromJson(basketsJson: String): List<BasketEntity> {
        val type = object : TypeToken<List<BasketEntity>>() {}.type
        return gson.fromJson(basketsJson, type)
    }
}