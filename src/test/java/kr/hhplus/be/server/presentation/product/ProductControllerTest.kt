package kr.hhplus.be.server.presentation.product

import com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.epages.restdocs.apispec.SimpleType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
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
class ProductControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @DisplayName("상품 조회 API - 성공")
    @Test
    fun getProduct() {
        // given
        val productId = 1L

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/products/{productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.productId").value(productId))
            .andExpect(jsonPath("$.productName").exists())
            .andExpect(jsonPath("$.salePrice").exists())
            .andExpect(jsonPath("$.availableStockCnt").exists())
            .andExpect(jsonPath("$.options").isArray())
            .andExpect(jsonPath("$.createdAt").exists())
            .andExpect(jsonPath("$.updatedAt").exists())
            .andDo(
                MockMvcRestDocumentation.document(
                    "get-product",
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("상품 조회 API")
                            .pathParameters(
                                parameterWithName("productId").type(SimpleType.NUMBER).description("상품 식별자")
                            )
                            .responseFields(
                                fieldWithPath("productId").type(JsonFieldType.NUMBER).description("상품 식별자"),
                                fieldWithPath("productName").type(JsonFieldType.STRING).description("상품 이름"),
                                fieldWithPath("salePrice").type(JsonFieldType.NUMBER).description("상품 가격"),
                                fieldWithPath("availableStockCnt").type(JsonFieldType.NUMBER).description("잔여 수량"),
                                fieldWithPath("options").type(JsonFieldType.ARRAY).description("상품 옵션 목록"),
                                fieldWithPath("options[].optionId").type(JsonFieldType.NUMBER).description("옵션 식별자").optional(),
                                fieldWithPath("options[].optionName").type(JsonFieldType.STRING).description("옵션 이름").optional(),
                                fieldWithPath("options[].optionPrice").type(JsonFieldType.NUMBER).description("옵션 가격").optional(),
                                fieldWithPath("options[].createdAt").type(JsonFieldType.STRING).description("옵션 생성 일시").optional(),
                                fieldWithPath("options[].updatedAt").type(JsonFieldType.STRING).description("옵션 수정 일시").optional(),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("상품 생성 일시"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("상품 수정 일시")
                            )
                            .build()
                    )
                )
            )
    }

    @DisplayName("상위 인기 판매 상품 조회 API - 성공")
    @Test
    fun getBestProducts() {
        // given
        val pageNumber = 1
        val pageSize = 10

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/products/best-sellers")
                .param("pageNumber", pageNumber.toString()) // 문자열로 요청, controller 단 Integer 타입 변경
                .param("pageSize", pageSize.toString())
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.items").isArray())
            .andExpect(jsonPath("$.items[0].productId").exists())
            .andExpect(jsonPath("$.items[0].productName").exists())
            .andExpect(jsonPath("$.items[0].salePrice").exists())
            .andExpect(jsonPath("$.items[0].canApplyCoupon").exists())
            .andExpect(jsonPath("$.items[0].totalSalesCount").exists())
            .andExpect(jsonPath("$.items[0].ranking").exists())
            .andExpect(jsonPath("$.items[0].aggregationStartDate").exists())
            .andExpect(jsonPath("$.pageNumber").value(pageNumber))
            .andExpect(jsonPath("$.pageSize").value(pageSize))
            .andDo(
                MockMvcRestDocumentation.document(
                    "get-best-selling-products",
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("상위 인기 판매 상품 조회 API")
                            .queryParameters(
                                parameterWithName("pageNumber").description("페이지 번호"),
                                parameterWithName("pageSize").description("페이지 크기")
                            )
                            .responseFields(
                                fieldWithPath("items").type(JsonFieldType.ARRAY).description("인기 상품 목록"),
                                fieldWithPath("items[].productId").type(JsonFieldType.NUMBER).description("상품 식별자").optional(),
                                fieldWithPath("items[].productName").type(JsonFieldType.STRING).description("상품명").optional(),
                                fieldWithPath("items[].salePrice").type(JsonFieldType.NUMBER).description("판매 가격").optional(),
                                fieldWithPath("items[].canApplyCoupon").type(JsonFieldType.BOOLEAN).description("쿠폰 적용 가능 여부").optional(),
                                fieldWithPath("items[].totalSalesCount").type(JsonFieldType.NUMBER).description("총 판매량").optional(),
                                fieldWithPath("items[].ranking").type(JsonFieldType.NUMBER).description("랭킹 순위").optional(),
                                fieldWithPath("items[].aggregationStartDate").type(JsonFieldType.STRING).description("집계 시작 일자").optional(),
                                fieldWithPath("pageNumber").type(JsonFieldType.NUMBER).description("현재 페이지 번호"),
                                fieldWithPath("pageSize").type(JsonFieldType.NUMBER).description("페이지 ㅅㅏㅇㅣㅈㅡ ")
                            )
                            .build()
                    )
                )
            )
    }
}
