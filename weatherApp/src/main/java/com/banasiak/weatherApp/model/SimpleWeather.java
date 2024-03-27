package com.banasiak.weatherApp.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SimpleWeather {

    private int temp;
    private String weatherImg;
    private String time;

}
