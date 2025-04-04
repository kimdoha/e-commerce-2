package kr.hhplus.be.server.presentation.order

import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.presentation.order.dto.PaymentConfirmRequest
import org.junit.jupiter.api.Assertions.*
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
class PaymentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("결제 확인 API - 성공")
    @Test
    fun confirmPayment() {
        // given
        val request = PaymentConfirmRequest(
            orderNo = "order-12345",
            totalPaymentAmount = 10000,
            userId = 1L
        )

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/payments/confirm")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.paymentNo").exists())
            .andExpect(jsonPath("$.payType").exists())
            .andExpect(jsonPath("$.lastPaymentAmount").exists())
            .andExpect(jsonPath("$.payYmdt").exists())
            .andDo(
                MockMvcRestDocumentation.document(
                    "confirm-payment",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("결제 확인 API")
                            .requestFields(
                                fieldWithPath("orderNo").type(JsonFieldType.STRING).description("주문 식별자"),
                                fieldWithPath("totalPaymentAmount").type(JsonFieldType.NUMBER).description("총 결제 금액"),
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("유저 식별자")
                            )
                            .responseFields(
                                fieldWithPath("paymentNo").type(JsonFieldType.NUMBER).description("결제 식별자"),
                                fieldWithPath("payType").type(JsonFieldType.STRING).description("결제 타입"),
                                fieldWithPath("lastPaymentAmount").type(JsonFieldType.NUMBER).description("최종 결제 금액"),
                                fieldWithPath("payYmdt").type(JsonFieldType.STRING).description("결제 일자"),
                                fieldWithPath("payStatusType").type(JsonFieldType.STRING).description("결제 상태 타입")
                            )
                            .build()
                    )
                )
            )
    }
}
