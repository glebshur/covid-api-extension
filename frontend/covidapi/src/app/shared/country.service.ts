import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { Country } from './models/country';
import { Observable } from 'rxjs';

/**
 * Provides data about countries that are allowed in the api.
 */
@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http: HttpClient) { }

  /**
   * Gets all ountries.
   * 
   * @returns observable with all countries
   */
  getAll() : Observable<Country[]>{
    return this.http.get<Country[]>(`${environment.covidApiUrl}/countries/all`)

  }
}
