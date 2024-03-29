package com.banasiak.weatherApp.mapper;

import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.SimpleWeather;
import com.banasiak.weatherApp.service.WeatherImgService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.banasiak.weatherApp.service.WeatherImgService.setImg;


public class TodayWeatherMapper {

    public static List<SimpleWeather> mapAllInfoDtoToTodayWeather(AllInfoDto allInfoDto){


        return allInfoDto.getWeather().stream()
                .filter(weatherInfo -> weatherInfo.getDateTime().getDayOfMonth()
                        == LocalDateTime.now().getDayOfMonth())
                .map(weatherInfo -> SimpleWeather.builder()
                        .temp(weatherInfo.getTemp())
                        .weatherImg(setImg(weatherInfo.getWeatherId(), allInfoDto.getSunset()))
                        .time(weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .build()
                )
                .toList();
    }

    public static List<SimpleWeather> mapAllInfoDtoToNextDayWeather(AllInfoDto allInfoDto){

        return allInfoDto.getWeather().stream()
                .filter(weatherInfo -> weatherInfo.getDateTime().getHour() == 12)
                .map(weatherInfo -> SimpleWeather.builder()
                        .temp(weatherInfo.getTemp())
                        .weatherImg(setImg(weatherInfo.getWeatherId(), "23:59"))
                        .time(weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM")))
                        .build()
                )
                .toList();
    }







}
