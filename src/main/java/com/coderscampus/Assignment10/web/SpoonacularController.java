package com.coderscampus.Assignment10.web;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.Assignment10.dto.DayResponse;
import com.coderscampus.Assignment10.dto.WeekResponse;


@RestController
public class SpoonacularController {

	RestTemplate temp = new RestTemplate();
	
	@GetMapping("mealplanner/week")
	public ResponseEntity<WeekResponse> getWeekMeals(@RequestParam Optional<String> numCalories, @RequestParam Optional<String> diet, @RequestParam Optional<String> exclusions) {
	
		
 		URI url = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/generate")
 									  .queryParam("timeFrame", "week")
 									  .queryParam("diet", diet)
 									  .queryParam("targetCalories", numCalories.isPresent()? numCalories.get():0)
 									  .queryParam("exclude", exclusions)				
 									  .queryParam("apiKey", "376f68413e1448c982fc087cc34bee90")
 									  .build()
									  .toUri();
 		
		ResponseEntity<WeekResponse> weekResponse = temp.getForEntity(url, WeekResponse.class);
		return weekResponse;
	}

	@GetMapping("mealplanner/day")
	public ResponseEntity<DayResponse> getDayMeals(@RequestParam Optional<String> numCalories, @RequestParam Optional<String> diet, @RequestParam Optional<String> exclusions) {
		

		URI url = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/generate")
									  .queryParam("timeFrame", "day")
									  .queryParam("diet", diet)
									  .queryParam("targetCalories", numCalories.isPresent()?numCalories.get():0)
									  .queryParam("exclude", exclusions)		
									  .queryParam("apiKey", "376f68413e1448c982fc087cc34bee90")
									  .build()
									  .toUri();
		
		ResponseEntity<DayResponse> dayResponse = temp.getForEntity(url, DayResponse.class);
		return dayResponse;
	
	}
}
