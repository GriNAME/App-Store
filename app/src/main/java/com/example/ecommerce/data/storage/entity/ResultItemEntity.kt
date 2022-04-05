package com.example.ecommerce.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.ecommerce.data.api.model.ResultItemDto
import com.example.ecommerce.data.util.Constants.TABLE_STORE_NAME
import com.example.ecommerce.data.util.GsonConverter
import com.example.ecommerce.domain.model.ResultItem
import kotlinx.coroutines.flow.Flow

@Entity(tableName = TABLE_STORE_NAME)
@TypeConverters(GsonConverter::class)
data class ResultItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String
)

fun ResultItemDto.mapToResultItemEntity() = ResultItemEntity("0")