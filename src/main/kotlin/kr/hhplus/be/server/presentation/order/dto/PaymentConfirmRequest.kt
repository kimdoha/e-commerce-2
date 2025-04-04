package kr.hhplus.be.server.presentation.order.dto

/**
 * @author Doha Kim
 */
data class PaymentConfirmRequest(
    val orderNo: String,
    val totalPaymentAmount: Int,
    val userId: Long,
)
