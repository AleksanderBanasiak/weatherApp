package com.banasiak.weatherApp.dto;


import lombok.Getter;

@Getter
public class OpenWeatherList {

    private OpenWeatherMain main;
    private OpenWeatherWeather[] weather;
    private OpenWeatherWind wind;
    private String dt;

}
