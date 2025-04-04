package kr.hhplus.be.server.presentation.product.dto

import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class BestSellerProductsResponse (
    val items: List<BestSellerProductResponse>,
    val pageNumber: Int,
    val pageSize: Int,
) {
    data class BestSellerProductResponse(
        val productId: Long,
        val productName: String,
        val salePrice: Int,
        val canApplyCoupon: Boolean,
        val totalSalesCount: Int,
        val ranking: Int,
        val aggregationStartDate: LocalDateTime,
    )
}
