package com.banasiak.weatherApp.service;

import com.banasiak.weatherApp.client.WeatherClient;
import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.DisplayWeatherDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

import static com.banasiak.weatherApp.mapper.DisplayWeatherDtoMapper.mapAllInfoDtoToDisplayWeatherDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {




    private final WeatherClient weatherClient;

    @Cacheable("Weather")
    public AllInfoDto getWeather(String city){
        return weatherClient.getWeatherForSpecificCity(city);
    }



    // totaj trzeba pobrac dla jednego wyswietlania


    public DisplayWeatherDto getWeatherOfDay(AllInfoDto allInfoDto){
        return mapAllInfoDtoToDisplayWeatherDto(allInfoDto);
    }



}
