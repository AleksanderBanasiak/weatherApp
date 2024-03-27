import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetWeatherComponent } from './components/get-weather/get-weather.component';


const routes: Routes = [
  {path: "", component: GetWeatherComponent}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
