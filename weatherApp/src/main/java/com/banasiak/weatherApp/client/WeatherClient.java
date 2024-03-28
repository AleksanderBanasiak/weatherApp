package com.banasiak.weatherApp.client;

import com.banasiak.weatherApp.dto.OpenWeatherCity;
import com.banasiak.weatherApp.dto.OpenWeatherJSONdto;
import com.banasiak.weatherApp.dto.OpenWeatherList;
import com.banasiak.weatherApp.dto.WeatherInfo;
import com.banasiak.weatherApp.model.AllInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherClient {

    public static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/forecast?appid=";
    public static final String API_KEY = "1cab780536115ed79a3bb5275726753f";

    private final RestTemplate restTemplate = new RestTemplate();

    public AllInfoDto getWeatherForSpecificCity(String city) {
        OpenWeatherJSONdto jsonDto;
        try {
            jsonDto = restTemplate.getForObject(WEATHER_URL + API_KEY
                    + "&q={city}&units=metric", OpenWeatherJSONdto.class, city);
        }catch ( Exception e){
            throw new IllegalArgumentException("City doesn't exist " + e.getMessage());
        }


        List<WeatherInfo> weather = extractWeatherInfo(List.of(jsonDto.getList()));
        return buildAllInfoDto(jsonDto, weather);
    }

    private List<WeatherInfo> extractWeatherInfo(List<OpenWeatherList> openWeatherLists) {
        List<WeatherInfo> weather = new ArrayList<>();
        for (OpenWeatherList openWeatherList : openWeatherLists) {
            WeatherInfo weatherInfo = WeatherInfo.builder()
                    .temp((int) openWeatherList.getMain().getTemp())
                    .minTemp((int) openWeatherList.getMain().getTemp_min())
                    .maxTemp((int) openWeatherList.getMain().getTemp_max())
                    .pressure((int) openWeatherList.getMain().getPressure())
                    .weatherId(openWeatherList.getWeather()[0].getId())
                    .description(openWeatherList.getWeather()[0].getDescription())
                    .wind((int) openWeatherList.getWind().getSpeed())
                    .dateTime(stringToLocalDateTimeConverter(openWeatherList.getDt()))
                    .build();
            weather.add(weatherInfo);
        }
        return weather;
    }

    private AllInfoDto buildAllInfoDto(OpenWeatherJSONdto jsonDto, List<WeatherInfo> weather) {
        return AllInfoDto.builder()
                .city(jsonDto.getCity().getName())
                .sunrise(unixToHour(jsonDto.getCity().getSunrise(), jsonDto.getCity().getTimezone()))
                .sunset(unixToHour(jsonDto.getCity().getSunset(), jsonDto.getCity().getTimezone()))
                .weather(weather)
                .build();
    }

    private LocalDateTime stringToLocalDateTimeConverter(String string) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(string)), ZoneOffset.UTC);
    }

    private String unixToHour(String unix, int timezone) {
        return LocalDateTime.ofEpochSecond(Long.parseLong(unix) + timezone, 0, ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}








