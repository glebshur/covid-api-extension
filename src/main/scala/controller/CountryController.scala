package shgo.innowise.trainee.covidapi
package controller

import cats.*
import cats.effect.*
import cats.implicits.*
import org.http4s.circe.*
import org.http4s.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.Status.*

import service.CountryService
import entity.Country


object CountryController :

  def getCountries : List[Country] =
    CountryService.getAll

//  def getCountries[F[_] : Monad] : Response[F] =


  def countryRoutes[F[_] : Monad] : HttpRoutes[F] =
    val dsl = Http4sDsl[F]
    import dsl._

    HttpRoutes.of[F]{
      case GET -> Root / "countries" / "all" => Ok(CountryService.getAll.asJson)
    }
