package com.banasiak.weatherApp.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WeatherInfo {

    private int temp;
    private int minTemp;
    private int maxTemp;
    private int pressure;

    private int weatherId;

    private String description;

    private int wind;

    private LocalDateTime dateTime;




}