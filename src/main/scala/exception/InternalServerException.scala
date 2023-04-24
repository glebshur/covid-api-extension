package shgo.innowise.trainee.covidapi
package exception

/** Exception caused by server errors
 * 
 * @param message exception description
 */
class InternalServerException(message : String) extends RuntimeException(message)
