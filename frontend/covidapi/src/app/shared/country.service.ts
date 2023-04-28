import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { Country } from './models/country';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http: HttpClient) { }

  getAll() : Observable<Country[]>{
    return this.http.get<Country[]>(`${environment.covidApiUrl}/countries/all`)
  }
}
