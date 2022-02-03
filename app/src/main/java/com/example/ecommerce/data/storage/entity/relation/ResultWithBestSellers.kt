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
        entityColumn = "id_result"
    )
    val bestSellers: List<BestSellerEntity>
)
