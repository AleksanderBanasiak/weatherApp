package com.banasiak.weatherApp.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class WeatherImgService {


    // xd
    public static String setImg(int id, String sunset){
        String img;

        LocalTime now = LocalTime.now();
        LocalTime whenSunSet = LocalTime.parse(sunset);

        boolean isDay = now.isBefore(whenSunSet);

        if(isDay){
            // day
             img = switch (id){
                case 800 -> "day_clear";
                case 801 -> "day_partial_cloud";
                case 802 -> "cloudy";
                case 803 -> "day_partial_cloud";
                case 804 -> "overcast";
                case 701 -> "mist";
                case 711 -> "mist";
                case 721 -> "fog";
                case 731 -> "day_clear";
                case 741 -> "fog";
                case 751 -> "day_clear";
                case 761 -> "day_clear";
                case 762 -> "day_clear";
                case 771 -> "tornado";
                case 781 -> "tornado";
                case 600 -> "snow";
                case 601 -> "snow";
                case 602 -> "snow";
                case 611 -> "sleet";
                case 612 -> "sleet";
                case 613 -> "sleet";
                case 615 -> "snow_thunder";
                case 616 -> "snow_thunder";
                case 620 -> "snow";
                case 621 -> "snow";
                case 622 -> "snow";
                default -> "";
            };
             if((id >= 300 && id <= 321) || (id >= 500 && id <= 531)){
                 img = "rain";
             }
             if(id >= 200 && id <= 232){
                img = "rain_thunder";
             }
        }else {
            // night
            img = switch (id){
                case 800 -> "night_full_moon_clear";
                case 801 -> "night_half_moon_partial_cloud";
                case 802 -> "night_half_moon_partial_cloud";
                case 803 -> "overcast";
                case 804 -> "overcast";
                case 701 -> "mist";
                case 711 -> "mist";
                case 721 -> "fog";
                case 731 -> "day_clear";
                case 741 -> "fog";
                case 751 -> "day_clear";
                case 761 -> "day_clear";
                case 762 -> "day_clear";
                case 771 -> "tornado";
                case 781 -> "tornado";
                case 600 -> "night_full_moon_snow";
                case 601 -> "night_full_moon_snow";
                case 602 -> "night_full_moon_snow";
                case 611 -> "night_full_moon_sleet";
                case 612 -> "night_full_moon_sleet";
                case 613 -> "night_full_moon_sleet";
                case 615 -> "night_full_moon_snow_thunder";
                case 616 -> "night_full_moon_snow_thunder";
                case 620 -> "night_full_moon_snow";
                case 621 -> "night_full_moon_snow";
                case 622 -> "night_full_moon_snow";
                default -> "";
            };
            if((id >= 300 && id <= 321) || (id >= 500 && id <= 531)){
                img = "night_half_moon_rain";
            }
            if(id >= 200 && id <= 232){
                img = "night_half_moon_rain_thunder";
            }
        }
        return img;
    }
}
