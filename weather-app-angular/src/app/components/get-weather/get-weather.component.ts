import { Component } from '@angular/core';
import { WeatherService } from '../../service/weather.service';
import { Weather } from '../../weather';


@Component({
  selector: 'app-get-weather',
  templateUrl: './get-weather.component.html',
  styleUrl: './get-weather.component.css'
})
export class GetWeatherComponent {

  weather: Weather = {} as Weather;

  cityName: string ='';

  isDay: boolean = true;

  constructor(private weatherService: WeatherService){

    
  }

  ngOnInit(){
    this.getWeatherForCity('katowice');
    
  }


  getWeatherForCity(cityName: string) {
    cityName = cityName.trim(); 
    cityName = cityName; 
    this.weatherService.getWeatherForCity(cityName).subscribe((res) => {
      this.weather = res;
    this.isDay = this.isNight();

    });
  }

  isNight(): boolean {
    if (!this.weather.time || !this.weather.sunrise || !this.weather.sunset) {
      return false; 
    }
  
    const [hours1, minutes1] = this.weather.time.split(' ')[1].split(':').map(Number);
    const [hoursSunrise, minutesSunrise] = this.weather.sunrise.split(':').map(Number);
    const [hoursSunset, minutesSunset] = this.weather.sunset.split(':').map(Number);
  
    const currentTimeInMinutes = hours1 * 60 + minutes1;
    const sunriseTimeInMinutes = hoursSunrise * 60 + minutesSunrise;
    const sunsetTimeInMinutes = hoursSunset * 60 + minutesSunset;
  
  
    return currentTimeInMinutes > sunriseTimeInMinutes && currentTimeInMinutes < sunsetTimeInMinutes;
  }

  getWeatherForCityWithSecificDay(index: number, city: string) {
    this.weatherService.getWeatherForCityWithSpecicficDay(city, index).subscribe((res) =>{
      this.weather = res;
      this.isDay = this.isNight();
    });
  }

  
  


}
