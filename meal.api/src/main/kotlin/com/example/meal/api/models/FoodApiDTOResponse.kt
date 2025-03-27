package com.example.meal.api.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class FoodApiDTOResponse (
    @SerialName("idMeal") var id: Long,
    @SerialName("strMeal") var name: String,
    @SerialName("strInstructions") var description: String,
    @SerialName("strCategory") var category: String
)
@Serializable
data class MealsWrapper (val meals: List<FoodApiDTOResponse>? = emptyList())