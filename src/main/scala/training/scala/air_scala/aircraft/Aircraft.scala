package training.scala.air_scala.aircraft

import training.scala.air_scala.airport.{LongRunway, MediumRunway, ShortRunway, LandingSurface}


// To be used Later, maybe for abstract types
sealed trait AircraftManufacturer
case object Boeing extends AircraftManufacturer
case object McDonnellDouglas extends AircraftManufacturer
case object Airbus extends AircraftManufacturer
case object Bombardier extends AircraftManufacturer

sealed trait AircraftClass {
  val runwayType: LandingSurface
}

trait TurboProp extends AircraftClass {
  val runwayType = ShortRunway
}

trait NarrowBodyJet extends AircraftClass {
  val runwayType = MediumRunway
}

trait WideBodyJet extends AircraftClass {
  val runwayType = LongRunway
}


// todo: should we explain self type annotations?
trait AircraftModel { self: AircraftClass =>
  def seats: Map[SeatingClass, Seq[Seat]]

}

case class Aircraft(model: AircraftModel)

case class Airline(name: String, aircraft: Set[Aircraft])

sealed trait SeatingClass {
  val priority: Int
}

case object FirstClass extends SeatingClass {
  val priority = 1
}

case object BusinessClass extends SeatingClass {
  val priority = 2
}

case object EconomyPlus extends SeatingClass {
  val priority = 3
}

case object Economy extends SeatingClass {
  val priority = 4
}

sealed trait Seat {
  val row: Int
  val seat: Char
  val seatingClass: SeatingClass
}

case class FirstClassSeat(row: Int, seat: Char) extends Seat {
  final val seatingClass = FirstClass
}

case class BusinessClassSeat(row: Int, seat: Char) extends Seat {
  final val seatingClass = BusinessClass
}

case class EconomyPlusSeat(row: Int, seat: Char) extends Seat {
  final val seatingClass = EconomyPlus
}

case class EconomySeat(row: Int, seat: Char) extends Seat {
  final val seatingClass = Economy
}
