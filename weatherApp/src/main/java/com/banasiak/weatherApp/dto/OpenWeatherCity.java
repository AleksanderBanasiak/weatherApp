package com.banasiak.weatherApp.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OpenWeatherCity {

    private String name;
    private int timezone;
    private String sunrise;
    private String sunset;


}
