package training.scala.air_scala.airline

import training.scala.air_scala.aircraft._

import scala.reflect._

/**
 * Perfect core for future exercise regarding Abstract Types...
 * Passenger would have an abstract type Nationality, which would
 * need to be filled in on a concrete instance of Passenger
 *
 * Also, add "FrequentFlyer" info... maybe instead?
 */
case class Passenger(
                     firstName: String,
                     middleName: Option[String],
                     lastName:String,
                     seatPreference: SeatPosition,
                     seatingClass: SeatingClass,
                     frequentFlyer: FrequentFlyer)  {

}

//case class Canadian(name: String) extends Passenger
//case class American(name: String) extends Passenger


object Passenger {
}