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

import shgo.innowise.trainee.covidapi.exception.InternalServerException

/** Represents country controller. */
object CountryController :

  /** Gets countries that are allowed in API
   * 
   * @return list of countries
   */
  def getCountries : List[Country] =
    CountryService.getAll

  /** Specify controllers routes
   *
   * @tparam F  effectful operation
   * @return    routes
   */
  def countryRoutes[F[_] : Monad] : HttpRoutes[F] =
    val dsl = Http4sDsl[F]
    import dsl._

    HttpRoutes.of[F]{
      case GET -> Root / "countries" / "all" => {
        try{
          Ok(CountryService.getAll.asJson)
        } catch {
          case e : InternalServerException => InternalServerError(e.getMessage)
        }
      }
    }
