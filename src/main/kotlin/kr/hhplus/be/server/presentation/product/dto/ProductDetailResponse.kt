package kr.hhplus.be.server.presentation.product.dto

import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class ProductDetailResponse (
    val productId: Long,
    val productName: String,
    val salePrice: Int,
    val availableStockCnt: Int,
    val options: List<OptionDetailResponse> = emptyList(),
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

data class OptionDetailResponse(
    val optionId: Long,
    val optionName: String,
    val optionPrice: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
