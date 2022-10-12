package com.codedrcampus.Assignment10.web;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.codedrcampus.Assignment10.dto.DayResponse;
import com.codedrcampus.Assignment10.dto.WeekResponse;


@RestController
public class SpoonacularController {

	@SuppressWarnings("unchecked")
	@GetMapping("mealplanner/week")
	public ResponseEntity<WeekResponse> getWeekMeals(String numCalories, String diet, String exclusions) {
		return (ResponseEntity<WeekResponse>) getSpoonacularResponse(numCalories, diet, exclusions, "week", WeekResponse.class);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("mealplanner/day")
	public ResponseEntity<DayResponse> getDayMeals(String numCalories, String diet, String exclusions) {
		return (ResponseEntity<DayResponse>) getSpoonacularResponse(numCalories, diet, exclusions, "day", DayResponse.class);
	}

	@SuppressWarnings("deprecation")
	private ResponseEntity<?> getSpoonacularResponse(String numCalories, String diet, 
			String exclusions, String timeFrame, Class<?> responseClass) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.spoonacular.com/mealplanner/generate")
			    .queryParam("timeFrame", timeFrame)
			    .queryParam("apiKey", "376f68413e1448c982fc087cc34bee90");
		if (!StringUtils.isEmpty(numCalories)) {
			builder = builder.queryParam("targetCalories", Integer.parseInt(numCalories));
		}
		if (!StringUtils.isEmpty(diet)) {
			builder = builder.queryParam("diet", diet);
		}
		if (!StringUtils.isEmpty(exclusions)) {
			builder = builder.queryParam("exclude", exclusions);
		}
		
		URI uri = builder.build().toUri();
		
		RestTemplate rt = new RestTemplate();
		ResponseEntity<?> responseEntity = rt.getForEntity(uri, responseClass);
		return responseEntity;
	}
}
