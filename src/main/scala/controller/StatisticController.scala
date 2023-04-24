package shgo.innowise.trainee.covidapi
package controller

import cats.*
import cats.effect.*
import cats.implicits.*
import org.http4s.circe.*
import org.http4s.*
import io.circe.generic.auto.*
//import io.circe._
//import io.circe.generic.semiauto._
import io.circe.syntax.*
import io.circe.Parser
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.Status.*
import shgo.innowise.trainee.covidapi.entity.CountryGlobalStatistic
import shgo.innowise.trainee.covidapi.paramMatcher.*
import shgo.innowise.trainee.covidapi.service.*

import java.time.Instant

object StatisticController :

  def getCountriesStatistic(countries : List[String],
                            from : Instant,
                            to : Instant):  List[CountryGlobalStatistic] =
    StatisticService.getCountryGlodbalStatistic(countries, from, to)

  def countryStatisticRoutes[F[_] : Monad] : HttpRoutes[F] =
    val dsl = Http4sDsl[F]
    import dsl._

    val fromDateQueryParamMatcher = InstantQueryParamMatcher("from")
    val toDateQueryParamMatcher = InstantQueryParamMatcher("to")

    HttpRoutes.of[F] {
      case GET -> Root / "statistics" :? CountriesQueryParamMatcher(coutries)
        +& fromDateQueryParamMatcher(from) +& toDateQueryParamMatcher(to) => Ok(getCountriesStatistic(coutries, from, to).asJson)
    }


