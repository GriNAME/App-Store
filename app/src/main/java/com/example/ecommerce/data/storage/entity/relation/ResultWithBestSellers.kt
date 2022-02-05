package com.example.ecommerce.data.storage.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.ecommerce.data.storage.entity.BestSellerEntity
import com.example.ecommerce.data.storage.entity.ResultItemEntity

data class ResultWithBestSellers(
    @Embedded
    val resultItemEntity: ResultItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val bestSellers: List<BestSellerEntity>
)
