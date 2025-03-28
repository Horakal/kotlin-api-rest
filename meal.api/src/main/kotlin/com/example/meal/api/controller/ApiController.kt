package com.example.meal.api.controller

import com.example.meal.api.exception.FoodNotFoundException
import com.example.meal.api.models.FoodDTOResponse
import com.example.meal.api.models.mapper.toFoodDTOResponse
import com.example.meal.api.services.FoodService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val foodService: FoodService) {

    @GetMapping("/search-by-name/{name}")
    fun mealByName(@PathVariable name:String): ResponseEntity<List<FoodDTOResponse>> {
        val result = foodService.searchMealByName(name)
        return ResponseEntity.ok(result)
    }


}