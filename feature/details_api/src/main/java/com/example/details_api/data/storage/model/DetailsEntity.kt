package com.example.details_api.data.storage.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.details_api.data.api.model.DetailsDto
import com.example.details_api.domain.model.Details
import com.example.homestore_api.data.util.Constants.TABLE_DETAILS

@Entity(tableName = TABLE_DETAILS)
data class DetailsEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "CPU")
    val cPU: String,
    @ColumnInfo(name = "camera")
    val camera: String,
    @ColumnInfo(name = "capacity")
    val capacity: List<String>,
    @ColumnInfo(name = "color")
    val color: List<String>,
    @ColumnInfo(name = "images")
    val images: List<String>,
    @ColumnInfo(name = "isFavorites")
    val isFavorites: Boolean,
    @ColumnInfo(name = "price")
    val price: Int,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "sd")
    val sd: String,
    @ColumnInfo(name = "ssd")
    val ssd: String,
    @ColumnInfo(name = "title")
    val title: String
)

fun DetailsDto.mapToEntity() = DetailsEntity(
    id,
    cPU,
    camera,
    capacity,
    color,
    images,
    isFavorites,
    price,
    rating,
    sd,
    ssd,
    title
)

fun DetailsEntity.mapToModel() = Details(
    cPU,
    camera,
    capacity,
    color,
    id,
    images,
    isFavorites,
    price,
    rating,
    sd,
    ssd,
    title
)
