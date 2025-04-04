package kr.hhplus.be.server.presentation.order

import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.presentation.order.dto.OrderCreateRequest
import kr.hhplus.be.server.presentation.order.dto.OrderItemRequest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @author Doha Kim
 */
@AutoConfigureRestDocs
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class OrderControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("주문 생성 API - 성공")
    @Test
    fun createOrder() {
        // given
        val request = OrderCreateRequest(
            userId = 1L,
            orderItems = listOf(
                OrderItemRequest(
                    productId = 10001L,
                    optionId = 20001L,
                    quantity = 2,
                )
            )
        )

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.userId").exists())
            .andExpect(jsonPath("$.orderItems").isArray())
            .andExpect(jsonPath("$.orderItems[0].orderProductId").exists())
            .andExpect(jsonPath("$.orderItems[0].productId").exists())
            .andExpect(jsonPath("$.orderItems[0].productName").exists())
            .andExpect(jsonPath("$.orderItems[0].salePrice").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions").isArray())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].orderOptionId").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].orderNo").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].optionId").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].optionName").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].orderCnt").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].optionPrice").exists())
            .andExpect(jsonPath("$.orderItems[0].orderOptions[0].orderStatusType").exists())
            .andExpect(jsonPath("$.totalOrderAmount").exists())
            .andExpect(jsonPath("$.coupons").isArray())
            .andExpect(jsonPath("$.coupons[0].couponId").exists())
            .andExpect(jsonPath("$.coupons[0].couponName").exists())
            .andExpect(jsonPath("$.coupons[0].couponDiscountAmount").exists())
            .andExpect(jsonPath("$.coupons[0].couponUsed").exists())
            .andDo(
                MockMvcRestDocumentation.document(
                    "create-order",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("주문 생성 API")
                            .requestFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                fieldWithPath("orderItems[]").type(JsonFieldType.ARRAY).description("주문 아이템 목록"),
                                fieldWithPath("orderItems[].productId").type(JsonFieldType.NUMBER).description("상품 식별자"),
                                fieldWithPath("orderItems[].optionId").type(JsonFieldType.NUMBER).description("옵션 식별자"),
                                fieldWithPath("orderItems[].quantity").type(JsonFieldType.NUMBER).description("주문 수량")
                            )
                            .responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                fieldWithPath("orderItems").type(JsonFieldType.ARRAY).description("주문 상품 목록"),
                                fieldWithPath("orderItems[].orderProductId").type(JsonFieldType.NUMBER).description("주문 상품 번호").optional(),
                                fieldWithPath("orderItems[].productId").type(JsonFieldType.NUMBER).description("상품 식별자").optional(),
                                fieldWithPath("orderItems[].productName").type(JsonFieldType.STRING).description("상품 이름").optional(),
                                fieldWithPath("orderItems[].salePrice").type(JsonFieldType.NUMBER).description("상품 판매가").optional(),
                                fieldWithPath("orderItems[].orderOptions").type(JsonFieldType.ARRAY).description("주문 옵션 목록").optional(),
                                fieldWithPath("orderItems[].orderOptions[].orderOptionId").type(JsonFieldType.NUMBER).description("주문 옵션 번호").optional(),
                                fieldWithPath("orderItems[].orderOptions[].orderNo").type(JsonFieldType.STRING).description("주문 번호").optional(),
                                fieldWithPath("orderItems[].orderOptions[].optionId").type(JsonFieldType.NUMBER).description("옵션 식별자").optional(),
                                fieldWithPath("orderItems[].orderOptions[].optionName").type(JsonFieldType.STRING).description("옵션 이름").optional(),
                                fieldWithPath("orderItems[].orderOptions[].orderCnt").type(JsonFieldType.NUMBER).description("주문 수량").optional(),
                                fieldWithPath("orderItems[].orderOptions[].optionPrice").type(JsonFieldType.NUMBER).description("옵션 정상가").optional(),
                                fieldWithPath("orderItems[].orderOptions[].orderStatusType").type(JsonFieldType.STRING).description("주문 상태 타입").optional(),
                                fieldWithPath("totalOrderAmount").type(JsonFieldType.NUMBER).description("총 주문 금액").optional(),
                                fieldWithPath("coupons").type(JsonFieldType.ARRAY).description("사용 가능한 쿠폰 목록"),
                                fieldWithPath("coupons[].couponId").type(JsonFieldType.NUMBER).description("쿠폰 식별자").optional(),
                                fieldWithPath("coupons[].couponName").type(JsonFieldType.STRING).description("쿠폰 이름").optional(),
                                fieldWithPath("coupons[].couponDiscountAmount").type(JsonFieldType.NUMBER).description("쿠폰 할인 금액").optional(),
                                fieldWithPath("coupons[].couponUsed").type(JsonFieldType.BOOLEAN).description("쿠폰 사용 여부").optional(),
                            )
                            .build()
                    )
                )
            )
    }
}
