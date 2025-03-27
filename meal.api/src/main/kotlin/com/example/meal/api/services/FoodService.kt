package com.example.meal.api.services

import com.example.meal.api.expection.FoodNotFoundException
import com.example.meal.api.models.FoodApiDTOResponse
import com.example.meal.api.porters.out.IFoodAPIRepository
import org.springframework.stereotype.Service

@Service
class FoodService( private val foodRepository:IFoodAPIRepository) {

    fun searchMealByName(foodName: String): List<FoodApiDTOResponse> {
        val data = foodRepository.searchMealByName(foodName)
        return data
    }
}