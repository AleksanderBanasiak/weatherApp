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

 

  getWeatherForCity(city: string): Observable<Weather> {
    return this.http.get<Weather>(`${BASIC_URL}/weather?city=${city}`);
  }
  

  getWeatherForCityWithSpecicficDay(city: string, day: number): Observable<Weather> {
    return this.http.get<Weather>(`${BASIC_URL}/weather?city=${city}&day=${day}`);
  }

}
