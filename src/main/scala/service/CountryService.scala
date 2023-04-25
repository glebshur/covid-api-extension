package shgo.innowise.trainee.covidapi
package service

import dataProvider.CountryDataProvider
import entity.Country

/** Provides country business logic. */
object CountryService :

  /** Gets all allowed countries in API.
   * 
   * @return list of coutries.
   */
  def getAll : List[Country] =
    CountryDataProvider.getCountries
