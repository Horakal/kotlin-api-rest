package com.example.meal.api.controller

import com.example.meal.api.exception.FoodNotFoundException
import com.example.meal.api.models.FoodDTOResponse
import com.example.meal.api.services.FoodService
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertNotNull


@ExtendWith(SpringExtension::class)
@WebMvcTest(ApiController::class)
class ApiControllerTest {

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<FoodService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: FoodService

    @Test
    fun `given a food test if return size`() {
        val food = "Arrabiata"
        val exceptedReturn = buildList<FoodDTOResponse> { add(FoodDTOResponse(name = "", description = "", category = ""))  }

        every { service.searchMealByName(food) } returns exceptedReturn

        val result = mockMvc.perform(get("/search-by-name/$food"))
            .andExpect(status().isOk)
            .andReturn()
        val json = result.response.contentAsString
        val objectMapper = jacksonObjectMapper()
        val foods: List<FoodDTOResponse> = objectMapper.readValue(json,  object : TypeReference<List<FoodDTOResponse>>() {})

        assertNotNull(foods)
        assertEquals(1, foods.size)
        verify { service.searchMealByName(food) }

    }

    @Test
    fun `given unknown food in url then check for not found`() {
        every { service.searchMealByName("kok") } throws FoodNotFoundException("Alimento não encontrado")
        val result = mockMvc.perform(get("/search-by-name/kok"))
            .andExpect(status().isNotFound)
            .andReturn()

    }
}