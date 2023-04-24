package shgo.innowise.trainee.covidapi
package service

import dataProvider.CountryDataProvider
import entity.Country

object CountryService :

  def getAll : List[Country] =
    CountryDataProvider.getCountries
