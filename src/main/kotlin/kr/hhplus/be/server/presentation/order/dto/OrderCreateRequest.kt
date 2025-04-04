package kr.hhplus.be.server.presentation.order.dto

/**
 * @author Doha Kim
 */
data class OrderCreateRequest (
    val userId: Long,
    val orderItems: List<OrderItemRequest>,
)

data class OrderItemRequest(
    val productId: Long,
    val optionId: Long,
    val quantity: Int,
)
