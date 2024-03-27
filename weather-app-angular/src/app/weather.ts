import { SimpleWeather } from "./simpleWeather";


export interface Weather{

    city: string;
    temp: number;
    description: string;
    weatherImg: number;
    time: string;
    sunrise: string;
    sunset: string;
    minDayTemp: number;
    maxDayTemp: number;
    pressure: number;
    wind: number;
    nextDays: Array<SimpleWeather>;
    today: Array<SimpleWeather>;

}