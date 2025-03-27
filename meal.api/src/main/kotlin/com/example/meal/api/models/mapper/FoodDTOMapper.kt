package com.example.meal.api.models.mapper

import com.example.meal.api.models.FoodApiDTOResponse
import com.example.meal.api.models.FoodDTOResponse

fun FoodApiDTOResponse.toFoodDTOResponse(): FoodDTOResponse {
    return FoodDTOResponse(name = name, description = description, category = category)
}
