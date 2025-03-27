package com.example.meal.api.repository

import com.example.meal.api.models.FoodApiDTOResponse
import com.example.meal.api.models.MealsWrapper
import com.example.meal.api.porters.out.IFoodAPIRepository
import kotlinx.serialization.ExperimentalSerializationApi
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlinx.serialization.json.Json
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Repository

@Repository
class TheMealApiRepository: IFoodAPIRepository {

    companion object {
        const val BASE_URL:String =  "https://www.themealdb.com/api/json/v1/1"
    }
    private val client = HttpClient.newHttpClient()

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json { ignoreUnknownKeys = true; explicitNulls= false }

    override fun searchMealByName(foodName: String): List<FoodApiDTOResponse> {
        val getSearchByMeal: String = "/search.php?s=$foodName"
        val request = HttpRequest.newBuilder().uri(URI.create(BASE_URL.plus(getSearchByMeal))).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        var meals: MealsWrapper = MealsWrapper()
        if (response.statusCode() == HttpStatus.OK.value())
            meals = json.decodeFromString(response.body())
        return meals.meals ?: emptyList()
    }


}