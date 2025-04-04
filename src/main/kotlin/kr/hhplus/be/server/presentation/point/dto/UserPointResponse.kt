package kr.hhplus.be.server.presentation.point.dto

import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
data class UserPointResponse (
    val userId: Long,
    val point: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)
