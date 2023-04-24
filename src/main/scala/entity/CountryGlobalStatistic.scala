package shgo.innowise.trainee.covidapi
package entity

import io.circe.generic.auto.*
import io.circe.syntax.*

case class CountryGlobalStatistic (country : String,
                                   minConfirmed : Int,
                                   maxConfirmed : Int) 
