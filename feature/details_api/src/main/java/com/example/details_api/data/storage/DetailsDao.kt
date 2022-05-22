package com.example.details_api.data.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.example.details_api.data.storage.model.*
import kotlinx.coroutines.flow.Flow


@Dao
interface DetailsDao {

    @Query("SELECT * FROM details_table")
    fun getDetails(): Flow<DetailsEntity>

    @Insert(onConflict = IGNORE)
    suspend fun insertDetails(detailsEntity: DetailsEntity)

//    @Query(
//        "SELECT :oi.orderId, SUM(oi.quantity * p.price) AS grand_total," +
//                "FROM order_item oi" +
//                "JOIN PRODUCT p ON p.id = oi.productid" +
//                "WHERE oi.orderid = @OrderId" +
//                "GROUP BY oi.orderid ORDER BY forDay ASC"
//    )
//    fun getCartTotalPrice(oi: OrderItem, product: Product): Int
//
//    @Query("SELECT SUM(stepCount) as total, AVG(stepCount) as average " +
//                "FROM userFitnessDailyRecords " +
//                "WHERE forDay BETWEEN :startDay AND :endDay " +
//                "ORDER BY forDay ASC")
//    fun getUserFitnessSumAndAverageForLastThirtyDays(startDay: Date?, endDay: Date?): SumAveragePojo?
}