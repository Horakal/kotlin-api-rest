package com.example.meal.api.models

import com.fasterxml.jackson.annotation.JsonProperty

data class FoodDTOResponse (var name: String, var description: String, var category: String)