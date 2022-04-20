package com.example.homestore_api.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.homestore_api.data.api.model.ResultItemDto
import com.example.homestore_api.data.util.Constants.TABLE_STORE_NAME
import com.example.homestore_api.data.util.GsonConverter

@Entity(tableName = TABLE_STORE_NAME)
@TypeConverters(GsonConverter::class)
data class ResultItemEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String
)

fun ResultItemDto.mapToResultItemEntity() = ResultItemEntity("0")