package com.banasiak.weatherApp.mapper;

import com.banasiak.weatherApp.dto.WeatherInfo;
import com.banasiak.weatherApp.model.AllInfoDto;
import com.banasiak.weatherApp.model.DisplayWeatherDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static com.banasiak.weatherApp.mapper.TodayWeatherMapper.*;
import static com.banasiak.weatherApp.service.WeatherImgService.setImg;

public class DisplayWeatherDtoMapper {

    public static DisplayWeatherDto mapAllInfoDtoToDisplayWeatherDto(AllInfoDto allInfoDto){

        return DisplayWeatherDto.builder()
                .city(allInfoDto.getCity())
                .temp(allInfoDto.getWeather().get(0).getTemp())
                .description(allInfoDto.getWeather().get(0).getDescription())
                .weatherImg(setImg(allInfoDto.getWeather().get(0).getWeatherId(), allInfoDto.getTime(), allInfoDto.getSunset()))
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
    // in progress
    public static DisplayWeatherDto mapAllInfoDtoToDisplayWeatherDtoButForSpecificDay
    (AllInfoDto allInfoDto, int day){

        List<WeatherInfo> weather = allInfoDto.getWeather();
        List<WeatherInfo> weatherForSpecificDay = getWeatherForSpecificDay(weather, day);
        WeatherInfo weatherForSpecificDayAndHour = getWeatherForSpecificDayAndHour(getWeatherForSpecificDay(weather, day));

        if(weatherForSpecificDayAndHour != null){
            return DisplayWeatherDto.builder()
                    .city(allInfoDto.getCity())
                    .temp(weatherForSpecificDayAndHour.getTemp())
                    .description(weatherForSpecificDayAndHour.getDescription())
                    .weatherImg(setImg(weatherForSpecificDayAndHour.getWeatherId(), allInfoDto.getTime(), allInfoDto.getSunset()))
                    .time(setWeatherTime(day,allInfoDto, weatherForSpecificDayAndHour).format(DateTimeFormatter.ofPattern("dd.MM HH:mm")))
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
                    .pressure(weatherForSpecificDayAndHour.getPressure())
                    .wind(weatherForSpecificDayAndHour.getWind())
                    .nextDays(mapAllInfoDtoToNextDayWeather(allInfoDto))
                    .today(mapAllInfoDtoToTodayWeatherForSpecificDay(weatherForSpecificDay, allInfoDto.getTime(),  allInfoDto.getSunset()))
                    .build();
        }else {
            throw new IllegalArgumentException("Weather not found");
        }




    }

    private static LocalDateTime setWeatherTime(int day, AllInfoDto allInfoDto,  WeatherInfo weatherForSpecificDay) {
        if(day == 0){
            return LocalDateTime.of(LocalDate.from(LocalDateTime.now()), LocalTime.parse(allInfoDto.getTime(), DateTimeFormatter.ofPattern("HH:mm")));
        }
        return weatherForSpecificDay.getDateTime();
    }

    private static WeatherInfo getWeatherForSpecificDayAndHour(List<WeatherInfo> weather) {
        return weather.stream()
                .filter(weatherInfo ->  weatherInfo.getDateTime().getHour() == 12)
                .findFirst()
                .orElse(weather.get(0));
    }

    private static List<WeatherInfo> getWeatherForSpecificDay(List<WeatherInfo> weather, int day){
        return weather.stream()
                .filter(weatherInfo -> weatherInfo.getDateTime().getDayOfMonth() ==
                        LocalDateTime.now().plusDays(day).getDayOfMonth())
                .toList();
    }




}
