package shgo.innowise.trainee.covidapi

import org.scalatest.flatspec.*
import org.scalatest.matchers.should._
import shgo.innowise.trainee.covidapi.dataProvider.CountryDataProvider

class CountryDataProviderTest extends AnyFlatSpec with Matchers{

  "GetCountries" should "return list of countries" in {
    CountryDataProvider.getCountries should not be empty
  }
}
