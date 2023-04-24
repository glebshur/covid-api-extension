package shgo.innowise.trainee.covidapi
package paramMatcher

import org.http4s.QueryParamDecoder
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.implicits.*
import org.http4s.server.*

implicit val countriesQueryParamDecoder: QueryParamDecoder[List[String]] =
  QueryParamDecoder[String].map(countries => countries.split(",").toList)

object CountriesQueryParamMatcher extends QueryParamDecoderMatcher[List[String]]("countries")
