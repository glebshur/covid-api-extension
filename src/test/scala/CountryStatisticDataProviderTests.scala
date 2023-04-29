package shgo.innowise.trainee.covidapi

import org.scalatest.flatspec.*
import org.scalatest.matchers.should.*

import java.time.Instant
import shgo.innowise.trainee.covidapi.dataProvider.*
import shgo.innowise.trainee.covidapi.exception.BadRequestException

class CountryStatisticDataProviderTests extends AnyFlatSpec with Matchers{

  "GetCountryStatistic" should "return list with statistics with valid parameters" in {
    val country = "belarus"
    val fromDate = Instant.parse("2022-03-20T00:00:00Z")
    val toDate = Instant.parse("2022-04-20T00:00:00Z")

    CountryStatisticDataProvider.getCountryStatistic(country, fromDate, toDate) should not be empty
  }

  it should "throw BadRequestException when country is invalid" in {
    val country = "abc123"
    val fromDate = Instant.parse("2022-03-20T00:00:00Z")
    val toDate = Instant.parse("2022-04-20T00:00:00Z")

    assertThrows[BadRequestException] {
      CountryStatisticDataProvider.getCountryStatistic(country, fromDate, toDate)
    }
  }
}
