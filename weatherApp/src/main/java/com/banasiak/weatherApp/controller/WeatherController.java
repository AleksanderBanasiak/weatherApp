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
    private final WeatherImgService weatherImgService;

//    @GetMapping
//    public String getWeather(@RequestParam(required = false, defaultValue = "katowice") String city, Model model) {
//        WeatherDto weather = weatherService.getWeather(city);
//        String imageUrl = weatherImgService.setImg(weather.getImgId(), weather.getUnixTime());
//        model.addAttribute("image", imageUrl);
//        model.addAttribute("weather", weather);
//        return "index";
//    }


//        @GetMapping()
//        public AllInfoDto getWeather(@RequestParam(required = false, defaultValue = "katowice") String city, Model model) {
//
//          AllInfoDto weather = weatherService.getWeather(city);
//
//          // tutaj zamiast get(0) powinno byc podanie dnia domyślnie dzisiaj
//          String imageUrl = weatherImgService.setImg(weather.getWeather().get(0).getWeatherId(), weather.getSunset());
//
//
//            model.addAttribute("image", imageUrl);
//            model.addAttribute("weather", weather);
//
//            return weather;
//        }



        // ta metoda jest git bo zwaraca wszystkie dane początkowe czyli obecną pogodę dla katowic z całą reszą ukrytych informacji
//        @GetMapping("/xpp")
//        public ResponseEntity<DisplayWeatherDto> getWeather() {
//            AllInfoDto weather = weatherService.getWeather("katowice");
//            return ResponseEntity.ok(weatherService.getWeatherOfDay(weather));
//        }
        // nie wiem czy jest sens robić service dla img chyba lepiej zrobić to w angularze


    @GetMapping()
    public ResponseEntity<DisplayWeatherDto> getWeather(@RequestParam(required = false, defaultValue = "katowice") String city) {
            AllInfoDto weather = weatherService.getWeather(city);
        DisplayWeatherDto weatherOfDay = weatherService.getWeatherOfDay(weather);
        return ResponseEntity.ok(weatherOfDay);
    }





}
