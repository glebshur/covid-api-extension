package shgo.innowise.trainee.covidapi
package entity

/** Represents covid API country.
 *
 * @param country country name
 * @param code    country name to use by request creating
 */
case class Country (val country : String, val code : String)