package kr.hhplus.be.server.presentation.point

import kr.hhplus.be.server.presentation.point.dto.PointChargeRequest
import kr.hhplus.be.server.presentation.point.dto.UserPointResponse
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@RestController
class PointController {
    @PostMapping("/points")
    fun chargePoints(
        @RequestBody request: PointChargeRequest,
    ): UserPointResponse = UserPointResponse(
        userId = request.userId,
        point = request.amount,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )

    @GetMapping("/points")
    fun getUserPoints(): UserPointResponse = UserPointResponse(
        userId = 1L,
        point = 0L,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )
}
