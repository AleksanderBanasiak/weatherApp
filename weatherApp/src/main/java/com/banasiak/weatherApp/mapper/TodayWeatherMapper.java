package com.banasiak.weatherApp.mapper;

import com.banasiak.weatherApp.dto.WeatherInfo;
import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.SimpleWeather;
import com.banasiak.weatherApp.service.WeatherImgService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.banasiak.weatherApp.service.WeatherImgService.setImg;


public class TodayWeatherMapper {

    public static List<SimpleWeather> mapAllInfoDtoToTodayWeather(AllInfoDto allInfoDto){


        return allInfoDto.getWeather().stream()
                .filter(weatherInfo -> weatherInfo.getDateTime().getDayOfMonth()
                        == LocalDateTime.now().getDayOfMonth())
                .map(weatherInfo -> SimpleWeather.builder()
                        .temp(weatherInfo.getTemp())
                        .weatherImg(setImg(weatherInfo.getWeatherId(), allInfoDto.getTime(),allInfoDto.getSunset()))
                        .time(weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .build()
                )
                .toList();
    }
    public static List<SimpleWeather> mapAllInfoDtoToTodayWeatherForSpecificDay(List<WeatherInfo> weatherInfos,String now,  String sunset){


        return weatherInfos.stream()

                .map(weatherInfo -> SimpleWeather.builder()
                        .temp(weatherInfo.getTemp())
                        .weatherImg(setImg(weatherInfo.getWeatherId(), weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")), sunset))
                        .time(weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .build()
                )
                .toList();
    }

    public static List<SimpleWeather> mapAllInfoDtoToNextDayWeather(AllInfoDto allInfoDto){

        SimpleWeather simpleWeather = allInfoDto.getWeather().stream()
                .filter(weatherInfo -> weatherInfo.getDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                .map(weatherInfo -> SimpleWeather.builder()
                        .temp(weatherInfo.getTemp())
                        .weatherImg(setImg(weatherInfo.getWeatherId(), null, null))
                        .time(weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM")))
                        .build()
                )
                .findFirst()
                .orElse(null);

        List<SimpleWeather> mappedList = new ArrayList<>();
        mappedList.add(simpleWeather);
        mappedList.addAll(allInfoDto.getWeather().stream()
                .filter(weatherInfo -> weatherInfo.getDateTime().getHour() == 12 && weatherInfo.getDateTime().getDayOfMonth() != LocalDateTime.now().getDayOfMonth() )
                .map(weatherInfo -> SimpleWeather.builder()
                        .temp(weatherInfo.getTemp())
                        .weatherImg(setImg(weatherInfo.getWeatherId(), null, null))
                        .time(weatherInfo.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM")))
                        .build()
                )
                .toList());


        return mappedList;
    }







}
