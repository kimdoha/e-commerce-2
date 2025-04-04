package kr.hhplus.be.server.presentation.coupon

import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.presentation.coupon.dto.CouponIssueRequest
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
class CouponControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("선착순 쿠폰 발급-성공")
    @Test
    fun issueCoupon() {
        // given
        val request = CouponIssueRequest(userId = 1L, couponId = 12345L)

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/coupons/issue")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.couponIssuedId").exists())
            .andExpect(jsonPath("$.couponId").exists())
            .andExpect(jsonPath("$.couponName").exists())
            .andExpect(jsonPath("$.couponIssuedYmdt").exists())
            .andDo(
                MockMvcRestDocumentation.document(
                    "issue-coupon",
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("선착순 쿠폰 발급 API")
                            .requestFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 식별자"),
                                fieldWithPath("couponId").type(JsonFieldType.NUMBER).description("쿠폰 식별자")
                            )
                            .responseFields(
                                fieldWithPath("couponIssuedId").type(JsonFieldType.NUMBER).description("쿠폰 발행 식별자"),
                                fieldWithPath("couponId").type(JsonFieldType.NUMBER).description("쿠폰 식별자"),
                                fieldWithPath("couponName").type(JsonFieldType.STRING).description("쿠폰 이름"),
                                fieldWithPath("couponIssuedYmdt").type(JsonFieldType.STRING).description("쿠폰 발행 일자")
                            ).build()
                    )
                )
            )
    }
}
