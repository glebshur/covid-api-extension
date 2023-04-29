package shgo.innowise.trainee.covidapi



import cats.*
import cats.effect.*
import cats.implicits.*
import org.http4s.circe.*
import org.http4s.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import org.http4s.dsl.*
import org.http4s.dsl.impl.*
import org.http4s.headers.*
import org.http4s.implicits.*
import org.http4s.server.*
import org.http4s.dsl.io.*
import org.http4s.implicits.*
import com.comcast.ip4s.*
import org.http4s.ember.server.*
import org.http4s.server.middleware.CORS
import shgo.innowise.trainee.covidapi.controller.*

/** Main server class. */
object CovidApiServer extends IOApp {

  /** Gets all routes.
   * 
   * @tparam F effectful operation
   * @return   all api routes
   */
  def allRoutes[F[_] : Monad] : HttpApp[F] =
    CORS.policy.withAllowOriginAll(
    (CountryController.countryRoutes[F] <+> StatisticController.countryStatisticRoutes[F]))
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
