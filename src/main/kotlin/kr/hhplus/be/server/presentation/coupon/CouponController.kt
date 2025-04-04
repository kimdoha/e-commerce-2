package kr.hhplus.be.server.presentation.coupon

import kr.hhplus.be.server.presentation.coupon.dto.CouponIssueRequest
import kr.hhplus.be.server.presentation.coupon.dto.IssuedCouponDetails
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@RestController
class CouponController {
    @PostMapping("/coupons/issue")
    fun issueCoupon(
        @RequestBody request: CouponIssueRequest
    ): IssuedCouponDetails = IssuedCouponDetails(
        couponIssuedId = 1L,
        couponId = 2L,
        couponName = "선착순 쿠폰",
        couponIssuedYmdt = LocalDateTime.now(),
    )
}
