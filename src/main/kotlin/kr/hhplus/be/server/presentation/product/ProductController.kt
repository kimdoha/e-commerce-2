package kr.hhplus.be.server.presentation.product

import kr.hhplus.be.server.presentation.product.dto.BestSellerProductsResponse
import kr.hhplus.be.server.presentation.product.dto.ProductDetailResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author Doha Kim
 */
@RestController
class ProductController {
    @GetMapping("/products/{productId}")
    fun getProduct(
        @PathVariable productId: Long,
    ): ProductDetailResponse = ProductDetailResponse(
        productId = productId,
        productName = "",
        salePrice = 0,
        availableStockCnt = 50,
        options = emptyList(),
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
    )

    @GetMapping("/products/best-sellers")
    fun getBestProducts(
        @RequestParam("pageNumber") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int,
    ): BestSellerProductsResponse = BestSellerProductsResponse(
        items = listOf(
            BestSellerProductsResponse.BestSellerProductResponse(
                productId = 1L,
                productName = "인기상품",
                salePrice = 10000,
                canApplyCoupon = true,
                totalSalesCount = 100,
                ranking = 50,
                aggregationStartDate = LocalDateTime.now(),
            )
        ),
        pageSize = pageSize,
        pageNumber = pageNumber,
    )
}
