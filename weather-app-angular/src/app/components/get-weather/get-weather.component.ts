import { Component } from '@angular/core';
import { WeatherService } from '../../service/weather.service';
import { Weather } from '../../weather';


@Component({
  selector: 'app-get-weather',
  templateUrl: './get-weather.component.html',
  styleUrl: './get-weather.component.css'
})
export class GetWeatherComponent {

  // weather: any;

  // weather: Weather;

  weather: Weather = {} as Weather;

  cityName: string ='';

  constructor(private weatherService: WeatherService){

    
  }

  ngOnInit(){
    this.getWeatherForCity('katowice');
  }


  getWeatherForCity(cityName: string) {
    cityName = cityName.trim(); // Usuń białe znaki z przodu i z tyłu
    cityName = cityName; 
    this.weatherService.getWeatherForCity(cityName).subscribe((res) => {
      console.log(res);
      this.weather = res;
    });
  }


  


  isNight(): boolean {
   
    const [date1, timeOfDay1] = this.weather.time.split(' ');
    const [day1, month1] = date1.split('.');

    const [hours2, minutes2] = this.weather.sunset.split(':').map(Number);

    const [hours1, minutes1] = timeOfDay1.split(':').map(Number);

  
    const dateObj1 = new Date(0, Number(month1) - 1, Number(day1), hours1, minutes1);
    const dateObj2 = new Date(0, Number(month1) - 1, Number(day1), hours2, minutes2);

    return dateObj1.getTime() > dateObj2.getTime();
  }

}
