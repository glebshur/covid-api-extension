package shgo.innowise.trainee.covidapi
package dataProvider

import sttp.client3.{SimpleHttpClient, UriContext}
import sttp.model.Uri

object ClientProvider :

  val basicUri : Uri = uri"https://api.covid19api.com"
  val client = SimpleHttpClient()
