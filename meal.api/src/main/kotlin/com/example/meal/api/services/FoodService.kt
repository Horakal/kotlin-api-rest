package com.example.meal.api.services

import com.example.meal.api.exception.FoodNotFoundException
import com.example.meal.api.models.FoodApiDTOResponse
import com.example.meal.api.models.FoodDTOResponse
import com.example.meal.api.models.mapper.toFoodDTOResponse
import com.example.meal.api.porters.out.IFoodAPIRepository
import org.springframework.stereotype.Service

@Service
class FoodService( private val foodRepository:IFoodAPIRepository) {

    fun searchMealByName(foodName: String): List<FoodDTOResponse> {
        val data = foodRepository.searchMealByName(foodName)
        when {
            data.isEmpty() -> throw FoodNotFoundException("Alimento não encontrado")
            else -> return data.map { it.toFoodDTOResponse() }
        }
    }
}