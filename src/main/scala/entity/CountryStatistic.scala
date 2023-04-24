package shgo.innowise.trainee.covidapi
package entity

import java.time.Instant

/** County covid confermed statistic for specified date.
 * 
 * @param country   country name
 * @param confirmed conbirmed cases
 * @param date      statistic date
 */
case class CountryStatistic (country : String,
                             confirmed : Int,
                             date: Instant)
