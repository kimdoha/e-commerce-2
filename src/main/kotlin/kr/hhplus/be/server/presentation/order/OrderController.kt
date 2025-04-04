package kr.hhplus.be.server.presentation.order

import kr.hhplus.be.server.presentation.order.dto.CouponDetailResponse
import kr.hhplus.be.server.presentation.order.dto.OrderCreateRequest
import kr.hhplus.be.server.presentation.order.dto.OrderDetailResponse
import kr.hhplus.be.server.presentation.order.dto.OrderItemDetailResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @author Doha Kim
 */
@RestController
class OrderController {
    @PostMapping("/orders")
    fun createOrder(
        @RequestBody order: OrderCreateRequest,
    ): OrderDetailResponse = OrderDetailResponse(
        userId = 1L,
        orderItems = listOf(
            OrderItemDetailResponse(
                orderProductId = 1L,
                productId = 2L,
                productName = "삼다수",
                salePrice = 5000,
                orderOptions = listOf(
                    OrderItemDetailResponse.OrderOptionResponse (
                        orderOptionId = 3L,
                        orderNo = "ORD12345",
                        optionId = 4L,
                        optionName = "1L 용량",
                        orderCnt = 2,
                        optionPrice = 1000,
                        orderStatusType = OrderItemDetailResponse.OrderStatusType.DEPOSIT_WAIT,
                    )
                )
            )
        ),
        totalOrderAmount = 7000,
        coupons = listOf(
            CouponDetailResponse(
                couponId = 1L,
                couponName = "신규가입 쿠폰",
                couponDiscountAmount = 2000,
                couponUsed = false,
            )
        )
    )
}
