package shgo.innowise.trainee.covidapi
package service

import shgo.innowise.trainee.covidapi.dataProvider.CountryStatisticDataProvider
import shgo.innowise.trainee.covidapi.entity.*

import java.time.Instant

object StatisticService :

  def getCountryGlodbalStatistic(countries : List[String],
                                 from : Instant,
                                 to : Instant) : List[CountryGlobalStatistic] =

    var countriesStat : List[CountryStatistic] = List()

    countries.foreach(country => countriesStat = countriesStat :::
      CountryStatisticDataProvider.getCountryStatistic(country, from, to))

    countriesStat.groupBy(_.country)
      .map(entry => CountryGlobalStatistic(entry._1, entry._2.minBy(_.confirmed).confirmed,
        entry._2.maxBy(_.confirmed).confirmed))
      .toList