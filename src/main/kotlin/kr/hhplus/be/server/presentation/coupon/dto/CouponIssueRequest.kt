package kr.hhplus.be.server.presentation.coupon.dto

/**
 * @author Doha Kim
 */
data class CouponIssueRequest (
    val userId: Long,
    val couponId: Long,
)
