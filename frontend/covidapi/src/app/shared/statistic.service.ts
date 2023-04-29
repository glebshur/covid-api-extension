import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Statistic } from './models/statistic';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

/**
 * Provides countries statistic from api.
 */
@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  constructor(private http: HttpClient) { }

  /**
   * Gets global statistic in period of time for specified countries.
   * 
   * @param countries countries names
   * @param from start interval
   * @param to end interval
   * @returns statistics
   */
  getAll(countries:string[], from:Date, to:Date) : Observable<Statistic[]>{

    const optios = {params : new HttpParams()
      .set("countries", countries.join())
      .set("from", from.toISOString())
      .set("to", to.toISOString())
    }

    return this.http.get<Statistic[]>(`${environment.covidApiUrl}/statistics`, optios)
  }
}
