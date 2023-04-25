package shgo.innowise.trainee.covidapi
package entity

import io.circe.generic.auto.*
import io.circe.syntax.*

/** Country covid statistic for specifed period of time.
 * 
 * @param country       country name
 * @param minConfirmed  min number of confermed cases in specified period of time
 * @param maxConfirmed  max number of confermed cases in specified period of time
 */
case class CountryGlobalStatistic (country : String,
                                   minConfirmed : Int,
                                   maxConfirmed : Int) 
