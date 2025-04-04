package kr.hhplus.be.server.presentation.coupon.dto

import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class IssuedCouponDetails (
    val couponIssuedId: Long,
    val couponId: Long,
    val couponName: String,
    val couponIssuedYmdt: LocalDateTime,
)
