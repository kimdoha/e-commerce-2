package kr.hhplus.be.server.presentation.order.dto

import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class PaymentResponse(
    val paymentNo: Long,
    val payType: String,
    val lastPaymentAmount: Int,
    val payYmdt: LocalDateTime,
    val payStatusType: PayStatusType,
) {
    enum class PayStatusType(val description: String) {
        PAYMENT_REQUEST("결제 요청"),
        PROCESSING("결제 진행중"),
        CANCEL("사용자 취소"),
        DONE("승인 성공"),
        FAIL("승인 실패"),
    }
}
