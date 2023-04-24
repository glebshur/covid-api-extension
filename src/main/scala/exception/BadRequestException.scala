package shgo.innowise.trainee.covidapi
package exception

/** Exception caused by invalid request parameters.
 * 
 * @param message exception description
 */
class BadRequestException(message : String) extends RuntimeException(message)
