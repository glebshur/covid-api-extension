package shgo.innowise.trainee.covidapi



import cats._
import cats.effect._
import cats.implicits._
import org.http4s.circe._
import org.http4s._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.dsl._
import org.http4s.dsl.impl._
import org.http4s.headers._
import org.http4s.implicits._
import org.http4s.server._
import com.comcast.ip4s.*
import org.http4s.ember.server.*
import shgo.innowise.trainee.covidapi.controller.*

/** Main server class. */
object CovidApiServer extends IOApp {

  /** Gets all routes.
   * 
   * @tparam F effectful operation
   * @return   all api routes
   */
  def allRoutes[F[_] : Monad] : HttpApp[F] =
    (CountryController.countryRoutes[F] <+> StatisticController.countryStatisticRoutes[F])
      .orNotFound


  override def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(allRoutes[IO])
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)

}
