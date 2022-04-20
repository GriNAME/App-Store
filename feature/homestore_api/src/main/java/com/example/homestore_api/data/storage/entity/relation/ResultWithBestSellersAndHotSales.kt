package com.example.homestore_api.data.storage.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.homestore_api.data.api.model.ResultItemDto
import com.example.homestore_api.data.storage.entity.*
import com.example.homestore_api.domain.model.ResultItem

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