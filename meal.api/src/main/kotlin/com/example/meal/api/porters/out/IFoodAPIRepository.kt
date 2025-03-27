package com.example.meal.api.porters.out

import com.example.meal.api.models.FoodApiDTOResponse

interface IFoodAPIRepository {

    fun searchMealByName(foodName: String):  List<FoodApiDTOResponse>
}
