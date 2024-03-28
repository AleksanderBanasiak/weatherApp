package com.banasiak.weatherApp.controller;

import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.DisplayWeatherDto;
import com.banasiak.weatherApp.model.SimpleWeather;
import com.banasiak.weatherApp.service.WeatherImgService;
import com.banasiak.weatherApp.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.banasiak.weatherApp.mapper.TodayWeatherMapper.mapAllInfoDtoToNextDayWeather;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class WeatherController {

    private final WeatherService weatherService;


    @GetMapping("/xd")
    public AllInfoDto xd(){

        return weatherService.getWeather("racibórz");
    }



    @GetMapping()
    public ResponseEntity<DisplayWeatherDto> getWeather(@RequestParam(required = false, defaultValue = "katowice") String city) {
            AllInfoDto weather = weatherService.getWeather(city);
        DisplayWeatherDto weatherOfDay = weatherService.getWeatherOfDay(weather);
        return ResponseEntity.ok(weatherOfDay);
    }





}
