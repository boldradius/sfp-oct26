package training.scala.air_scala.domain

// To be used Later, maybe for abstract types
sealed trait AircraftManufacturer
case object Boeing extends AircraftManufacturer
case object McDonnellDouglas extends AircraftManufacturer

// todo: subtypes of aircraft. We should have rotary, fixed wing,
// todo: and VSTOL (e.g. V22 Osprey, Marine Corp Spec F35, & AV8B Harrier)
// todo: engine type - TurboProp, Jet, Tiltrotor

sealed trait AircraftModel
case object MD11 extends AircraftModel
case object B747 extends AircraftModel
case object V22Osprey extends AircraftModel

case class Aircraft(aircraftModel: AircraftModel, id: String)
case class Airline(name: String, aircraft: Set[Aircraft])
case class Seat(row: Int, position: String)


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


sealed trait LandingSurface
case object LandingStrip extends LandingSurface
case object LandingPad extends LandingSurface
