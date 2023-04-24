package shgo.innowise.trainee.covidapi
package dataProvider

import sttp.client3.*
import sttp.client3.circe.*
import sttp.model.Uri
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.*
import shgo.innowise.trainee.covidapi.entity.*
import shgo.innowise.trainee.covidapi.exception.InternalServerException

object CountryDataProvider :

  implicit val countryDecoder: Decoder[Country] = countryCursor =>
    for {
      country <- countryCursor.get[String]("Country")
      slug <- countryCursor.get[String]("Slug")
    } yield Country(country, slug)

  def getCountries : List[Country] =
    val uri = ClientProvider.basicUri.addPath("countries")
    val request = basicRequest
      .get(uri)
      .response(asJson[List[Country]])

    val response = ClientProvider.client.send(request)
    response.body match {
      case Left(e)  => throw new InternalServerException("Cannot get countries information")
      case Right(r) => r
    }