package com.banasiak.weatherApp.mapper;

import com.banasiak.weatherApp.dto.WeatherInfo;
import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.DisplayWeatherDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.banasiak.weatherApp.mapper.TodayWeatherMapper.mapAllInfoDtoToNextDayWeather;
import static com.banasiak.weatherApp.mapper.TodayWeatherMapper.mapAllInfoDtoToTodayWeather;
import static com.banasiak.weatherApp.service.WeatherImgService.setImg;

public class DisplayWeatherDtoMapper {

    public static DisplayWeatherDto mapAllInfoDtoToDisplayWeatherDto(AllInfoDto allInfoDto){


        return DisplayWeatherDto.builder()
                .city(allInfoDto.getCity())
                .temp(allInfoDto.getWeather().get(0).getTemp())
                .description(allInfoDto.getWeather().get(0).getDescription())
                .weatherImg(setImg(allInfoDto.getWeather().get(0).getWeatherId(), allInfoDto.getSunset()))
                .time(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM HH:mm")))
                .sunrise(allInfoDto.getSunrise())
                .sunset(allInfoDto.getSunset())
                .minDayTemp(allInfoDto.getWeather().stream()
                        .filter(weatherInfo -> weatherInfo.getDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                        .mapToInt(WeatherInfo::getMaxTemp)
                        .min()
                        .orElseThrow(IllegalStateException::new))
                .maxDayTemp(allInfoDto.getWeather().stream()
                        .filter(weatherInfo -> weatherInfo.getDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth())
                        .mapToInt(WeatherInfo::getMaxTemp)
                        .max()
                        .orElseThrow(IllegalStateException::new))
                .pressure(allInfoDto.getWeather().get(0).getPressure())
                .wind(allInfoDto.getWeather().get(0).getWind())
                .nextDays(mapAllInfoDtoToNextDayWeather(allInfoDto))
                .today(mapAllInfoDtoToTodayWeather(allInfoDto))
                .build();

    }










}
