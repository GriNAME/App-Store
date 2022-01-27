package com.example.ecommerce.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.ecommerce.data.util.Constants.TABLE_HOME_NAME
import com.example.ecommerce.data.util.GsonConverter
import com.example.ecommerce.domain.model.ResultItem

@Entity(tableName = TABLE_HOME_NAME)
@TypeConverters(GsonConverter::class)
data class ResultItemEntity(
    @PrimaryKey(autoGenerate = false)
    val newId:Int =0,
    val bestSeller: List<BestSellerEntity>,
    val homeStore: List<HomeStoreEntity>,
    val id: String
) {
    fun toResultItem(): ResultItem = ResultItem(
        bestSeller = bestSeller.map { it.toBestSeller() },
        homeStore = homeStore.map { it.toHomeStore() },
        id = id
    )
}
