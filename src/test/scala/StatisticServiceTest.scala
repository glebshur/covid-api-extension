package shgo.innowise.trainee.covidapi

import org.scalatest.flatspec.*
import org.scalatest.matchers.should.*

import java.time.Instant
import shgo.innowise.trainee.covidapi.dataProvider.CountryStatisticDataProvider
import shgo.innowise.trainee.covidapi.entity.{CountryGlobalStatistic, CountryStatistic}
import shgo.innowise.trainee.covidapi.service.StatisticService

import java.time.temporal.ChronoUnit

class StatisticServiceTest extends AnyFlatSpec with Matchers {

  "GetCountryGlodbalStatistic" should "calculate global statistic" in {
    val countries = List("belarus")
    val fromDate = Instant.parse("2022-04-20T00:00:00.000Z")
    val toDate = Instant.parse("2022-04-23T00:00:01.000Z")

    val expectedItem = CountryGlobalStatistic("Belarus", 528, 695)

    StatisticService.getCountryGlodbalStatistic(countries, fromDate, toDate) should contain(expectedItem)
  }
}
