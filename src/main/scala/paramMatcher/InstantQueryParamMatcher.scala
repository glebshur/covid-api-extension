package shgo.innowise.trainee.covidapi
package paramMatcher

import org.http4s.QueryParamDecoder
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.implicits.*
import org.http4s.server.*

import java.time.Instant
import java.time.format.DateTimeFormatter

implicit val dateTimeQueryParamDecoder : QueryParamDecoder[Instant] =
  QueryParamDecoder[String].map(Instant.parse(_))

class InstantQueryParamMatcher(name : String) extends QueryParamDecoderMatcher[Instant](name = name)
