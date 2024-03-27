package com.banasiak.weatherApp.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DisplayWeatherDto {

    private String city;

    private int temp;

    private String description;

    private String weatherImg;
    private String time;

    private String sunrise;
    private String sunset;
    private int minDayTemp;
    private int maxDayTemp;
    private int pressure;
    private int wind;
    private List<SimpleWeather> nextDays;
    private List<SimpleWeather> today;




}
