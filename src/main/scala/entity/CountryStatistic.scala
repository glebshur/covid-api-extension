package shgo.innowise.trainee.covidapi
package entity

import java.time.Instant

case class CountryStatistic (country : String,
                             confirmed : Int,
                             date: Instant)
