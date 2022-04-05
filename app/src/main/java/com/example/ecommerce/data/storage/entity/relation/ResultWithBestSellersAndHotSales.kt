package com.example.ecommerce.data.storage.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.data.storage.entity.*
import com.example.ecommerce.domain.model.ResultItem

data class ResultWithBestSellersAndHotSales(
    @Embedded
    val resultItemEntity: ResultItemEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val bestSellerEntities: List<BestSellerEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val hotSalesEntities: List<HotSalesEntity>
)

fun ResultItemDto.mapToEntity() = ResultWithBestSellersAndHotSales(
    mapToResultItemEntity(),
    bestSeller.mapToEntities("0"),
    hotSales.mapToEntities("0")
)

fun ResultWithBestSellersAndHotSales.mapToModel() = ResultItem(
    resultItemEntity.id,
    bestSeller = bestSellerEntities.mapToModels(),
    hotSales = hotSalesEntities.mapToModels()
)

fun List<ResultWithBestSellersAndHotSales>.mapToModels() = map { entity ->
    entity.mapToModel()
}