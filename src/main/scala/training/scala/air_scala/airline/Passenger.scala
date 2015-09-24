package training.scala.air_scala.airline

/**
 * Perfect core for future exercise regarding Abstract Types...
 * Passenger would have an abstract type Nationality, which would
 * need to be filled in on a concrete instance of Passenger
 *
 * Also, add "FrequentFlyer" info... maybe instead?
 */
sealed trait Passenger
case class Canadian(name: String) extends Passenger
case class American(name: String) extends Passenger


