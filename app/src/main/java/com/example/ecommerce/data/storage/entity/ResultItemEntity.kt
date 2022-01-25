package com.example.ecommerce.data.storage.entity

import androidx.room.Entity
import com.example.ecommerce.data.util.Constants.TABLE_HOME_NAME

@Entity(tableName = TABLE_HOME_NAME)
data class ResultItemEntity(
    val bestSeller: List<BestSellerEntity>,
    val homeStore: List<HomeStoreEntity>,
    val id: String
)
