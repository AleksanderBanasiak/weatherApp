package com.banasiak.weatherApp.controller;

import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.DisplayWeatherDto;
import com.banasiak.weatherApp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.banasiak.weatherApp.mapper.DisplayWeatherDtoMapper.mapAllInfoDtoToDisplayWeatherDtoButForSpecificDay;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping("/xd")
    public ResponseEntity<DisplayWeatherDto> getWeatherxpp(@RequestParam(required = false, defaultValue = "katowice") String city) {
        AllInfoDto weather = weatherService.getWeather(city);



        return ResponseEntity.ok(mapAllInfoDtoToDisplayWeatherDtoButForSpecificDay(weather, 3));
    }
    @GetMapping("/xdd")
    public ResponseEntity<AllInfoDto> getWeatherxppd(@RequestParam(required = false, defaultValue = "katowice") String city) {
        AllInfoDto weather = weatherService.getWeather(city);



        return ResponseEntity.ok(weather);
    }


    @GetMapping()
    public ResponseEntity<DisplayWeatherDto> getWeather
            (@RequestParam(required = false, defaultValue = "katowice") String city,
             @RequestParam(required = false, defaultValue = "0") int day) {
            AllInfoDto weather = weatherService.getWeather(city);
        DisplayWeatherDto weatherOfDay = weatherService.getWeatherOfDay(weather, day);
        return ResponseEntity.ok(weatherOfDay);
    }

}
