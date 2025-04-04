package kr.hhplus.be.server.presentation.order

import kr.hhplus.be.server.presentation.order.dto.PaymentConfirmRequest
import kr.hhplus.be.server.presentation.order.dto.PaymentResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@RestController
class PaymentController {
    @PostMapping("/payments/confirm")
    fun confirmPayment(
        @RequestBody request: PaymentConfirmRequest
    ): PaymentResponse = PaymentResponse(
        paymentNo = 1L,
        payType = "POINT",
        lastPaymentAmount = 10000,
        payYmdt = LocalDateTime.now(),
        payStatusType = PaymentResponse.PayStatusType.DONE,
    )
}
