package com.banasiak.weatherApp.model;

import com.banasiak.weatherApp.dto.WeatherInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AllInfoDto {


    private String city;
    private String sunrise;
    private String sunset;
    private List<WeatherInfo> weather;



}
