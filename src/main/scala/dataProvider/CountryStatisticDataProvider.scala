package shgo.innowise.trainee.covidapi
package dataProvider

import sttp.client3.*
import sttp.client3.circe.*
import io.circe.Decoder

import java.time.format.DateTimeFormatter
import java.time.Instant
import entity.CountryStatistic

import shgo.innowise.trainee.covidapi.exception.BadRequestException

/** Provides countries statistic from covid API. */
object CountryStatisticDataProvider :

  implicit val countryDecoder: Decoder[CountryStatistic] = countryStatCursor =>
    for {
      country <- countryStatCursor.get[String]("Country")
      confermed <- countryStatCursor.get[Int]("Cases")
      date <- countryStatCursor.get[String]("Date")
    } yield CountryStatistic(country, confermed, Instant.parse(date))

  /** Gets country statistic for specified period of time.
   * 
   * @param country country name
   * @param from    start interval
   * @param to      end interval
   * @return        list of country statistics
   */
  def getCountryStatistic(country : String,
                          from : Instant,
                          to : Instant): List[CountryStatistic] =

    val uri = ClientProvider.basicUri.addPath("country").addPath(country).addPath("status").addPath("confirmed")
      .addParam("from", from.toString)
      .addParam("to", to.toString)
    val request = basicRequest
      .get(uri)
      .response(asJson[List[CountryStatistic]])

    val response = ClientProvider.client.send(request)
    response.body match {
      case Left(e) => throw new BadRequestException(s"Country name '${country}' is invalid")
      case Right(r) => r
    }
