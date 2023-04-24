package shgo.innowise.trainee.covidapi
package controller

import cats.*
import cats.effect.*
import cats.implicits.*
import org.http4s.circe.*
import org.http4s.*
import io.circe.generic.auto.*
import shgo.innowise.trainee.covidapi.exception.BadRequestException
import io.circe.syntax.*
import io.circe.Parser
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.Status.*
import shgo.innowise.trainee.covidapi.entity.CountryGlobalStatistic
import shgo.innowise.trainee.covidapi.paramMatcher.*
import shgo.innowise.trainee.covidapi.service.*

import java.time.Instant

/** Represents countries statistic controller. */
object StatisticController :

  /** Gets global statistic to cpecifed countries by specifed period of time
   *
   * @param countries countries names
   * @param from      start of period
   * @param to        end of period
   * @return          list of countries global statistic
   */
  def getCountriesStatistic(countries : List[String],
                            from : Instant,
                            to : Instant):  List[CountryGlobalStatistic] =

    if(from.isAfter(to) || from.equals(to))
      throw new BadRequestException("'From' have to be greater than 'to'")

    StatisticService.getCountryGlodbalStatistic(countries, from, to)

  /** Specifies controllers routes
   *
   * @tparam F  effectful operation
   * @return    routes
   */
  def countryStatisticRoutes[F[_] : Monad] : HttpRoutes[F] =
    val dsl = Http4sDsl[F]
    import dsl._

    val fromDateQueryParamMatcher = InstantQueryParamMatcher("from")
    val toDateQueryParamMatcher = InstantQueryParamMatcher("to")

    HttpRoutes.of[F] {
      case GET -> Root / "statistics" :? CountriesQueryParamMatcher(coutries)
        +& fromDateQueryParamMatcher(from) +& toDateQueryParamMatcher(to) => {

        try {
          Ok(getCountriesStatistic(coutries, from, to).asJson)
        } catch {
          case e : BadRequestException => BadRequest(e.getMessage.asJson)
        }
      }
    }


