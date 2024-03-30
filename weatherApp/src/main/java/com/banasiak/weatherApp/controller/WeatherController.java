package com.banasiak.weatherApp.controller;

import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.DisplayWeatherDto;
import com.banasiak.weatherApp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping()
    public ResponseEntity<DisplayWeatherDto> getWeather
            (@RequestParam(required = false, defaultValue = "katowice") String city,
             @RequestParam(required = false, defaultValue = "0") int day) {
            AllInfoDto weather = weatherService.getWeather(city);
        DisplayWeatherDto weatherOfDay = weatherService.getWeatherOfDay(weather, day);
        return ResponseEntity.ok(weatherOfDay);
    }

}
