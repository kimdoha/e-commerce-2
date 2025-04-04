package kr.hhplus.be.server.presentation.point

import com.epages.restdocs.apispec.ResourceDocumentation.headerWithName
import com.epages.restdocs.apispec.ResourceDocumentation.resource
import com.epages.restdocs.apispec.ResourceSnippetParameters
import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.presentation.point.dto.PointChargeRequest
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
class PointControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @DisplayName("포인트 충전하기-성공")
    @Test
    fun chargePoints() {
        // given
        val request = PointChargeRequest(userId = 1, amount = 10_000)

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.post("/points")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.userId").value(request.userId))
            .andExpect(jsonPath("$.point").value(request.amount))
            .andExpect(jsonPath("$.createdAt").exists())
            .andExpect(jsonPath("$.updatedAt").exists())
            .andDo(
                MockMvcRestDocumentation.document(
                    "post-charge-points",
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("포인트 충전 API")
                            .requestFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 식별자"),
                                fieldWithPath("amount").type(JsonFieldType.NUMBER).description("충전할 포인트 금액")
                            )
                            .responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 식별자"),
                                fieldWithPath("point").type(JsonFieldType.NUMBER).description("충전 후 포인트 잔액"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("포인트 생성 일시"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("포인트 수정 일시")
                            ).build()
                    )
                )
            )
    }

    @DisplayName("포인트 잔액 조회-성공")
    @Test
    fun getUserPoints() {
        // given
        val userId = 1L

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.get("/points")
                .header("X-USER-ID", userId.toString())
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.userId").exists())
            .andExpect(jsonPath("$.point").exists())
            .andExpect(jsonPath("$.createdAt").exists())
            .andExpect(jsonPath("$.updatedAt").exists())
            .andDo(
                MockMvcRestDocumentation.document(
                    "get-user-points",
                    resource(
                        ResourceSnippetParameters.builder()
                            .description("포인트 잔액 조회 API")
                            .requestHeaders(
                                headerWithName("X-USER-ID").description("사용자 식별자")
                            )
                            .responseFields(
                                fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 식별자"),
                                fieldWithPath("point").type(JsonFieldType.NUMBER).description("현재 포인트 잔액"),
                                fieldWithPath("createdAt").type(JsonFieldType.STRING).description("포인트 생성 일시"),
                                fieldWithPath("updatedAt").type(JsonFieldType.STRING).description("포인트 수정 일시")
                            )
                            .build()
                    )
                )
            )
    }
}
