import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Weather } from '../weather';

const BASIC_URL = ["http://localhost:8080"];

@Injectable({
  providedIn: 'root'
})
export class WeatherService {

  constructor(private http: HttpClient ) { }

  // getWeather(): Observable<any> {
  //   return this.http.get(BASIC_URL+"/weather/xpp");
  // }

  getWeather(weather: Weather): Observable<Weather> {
    return this.http.get<Weather>(BASIC_URL+"/weather/xpp");
  }
  

}
