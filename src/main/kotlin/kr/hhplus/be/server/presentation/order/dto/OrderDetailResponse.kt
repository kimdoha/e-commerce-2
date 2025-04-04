package kr.hhplus.be.server.presentation.order.dto

/**
 * @author Doha Kim
 */
data class OrderDetailResponse(
    val userId: Long,
    val orderItems: List<OrderItemDetailResponse>,
    val totalOrderAmount: Int,
    val coupons: List<CouponDetailResponse>,
)

data class OrderItemDetailResponse(
    val orderProductId: Long,
    val productId: Long,
    val productName: String,
    val salePrice: Int,
    val orderOptions: List<OrderOptionResponse>,
) {
    data class OrderOptionResponse(
        val orderOptionId: Long,
        val orderNo: String,
        val optionId: Long,
        val optionName: String,
        val orderCnt: Int,
        val optionPrice: Int,
        val orderStatusType: OrderStatusType,
    )

    enum class OrderStatusType(val description: String) {
        DEPOSIT_WAIT("입금 대기"),
        PAY_DONE("결제 완료"),
        BUY_CONFIRM("구매 확정"),
        PAY_CANCEL("결제 취소")
    }
}

data class CouponDetailResponse(
    val couponId: Long,
    val couponName: String,
    val couponDiscountAmount: Int,
    val couponUsed: Boolean,
)
