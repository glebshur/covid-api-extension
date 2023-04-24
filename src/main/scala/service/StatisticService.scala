package shgo.innowise.trainee.covidapi
package service

import shgo.innowise.trainee.covidapi.dataProvider.CountryStatisticDataProvider
import shgo.innowise.trainee.covidapi.entity.*

import java.time.Instant
import java.time.temporal.{ChronoUnit, TemporalUnit}

/** Represents statistic business logic. */
object StatisticService :

  /** Gets global statistic for each country in specified period of time.
   *
   * @param countries countries names
   * @param from      start interval
   * @param to        end interval
   * @return          list of global statistic
   */
  def getCountryGlodbalStatistic(countries : List[String],
                                 from : Instant,
                                 to : Instant) : List[CountryGlobalStatistic] =

    // need it to calculate statistic of 1-st day
    val periodStart = from.minus(1, ChronoUnit.DAYS)
    // collect global dayly statistic for each country
    var countriesGlobalStatistic : List[CountryGlobalStatistic] = List()
    for(country <- countries){
      val countryGlobalStatistic = calculateGlobalStatistic(
        CountryStatisticDataProvider.getCountryStatistic(country, periodStart, to))

      if(countryGlobalStatistic != null){
        countriesGlobalStatistic = countriesGlobalStatistic.appended(countryGlobalStatistic)
      }
    }
    countriesGlobalStatistic

  /** Calculates global dayly statistic.
   *
   * @param countryStatistics list of country statistic
   * @return                  global country statistic
   */
  def calculateGlobalStatistic(countryStatistics : List[CountryStatistic]) : CountryGlobalStatistic =

    var country = ""
    var minConfermed : Int = Int.MaxValue
    var maxConfermed : Int = 0

    if(countryStatistics.isEmpty){
      return null
    }

    for (i <- 1 to countryStatistics.length - 1) {
      val confermedPerDay = countryStatistics(i).confirmed - countryStatistics(i - 1).confirmed

      if(confermedPerDay > maxConfermed)
        maxConfermed = confermedPerDay
        country = countryStatistics(i).country

      if(confermedPerDay < minConfermed)
        minConfermed = confermedPerDay
        country = countryStatistics(i).country
    }
    CountryGlobalStatistic(country, minConfermed, maxConfermed)
