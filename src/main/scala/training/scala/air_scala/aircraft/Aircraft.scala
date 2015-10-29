package training.scala.air_scala.aircraft

import training.scala.air_scala.airline.Passenger
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

case class Plane(availableSeats: Set[Seat], assignedSeats: Set[(Seat, Passenger)]) {
  def reserve(passenger: Passenger): Option[(Seat, Plane)] = {
    val availableClassSeats = availableSeats.filter(_.seatingClass == passenger.seatingClass)

    availableClassSeats
      .find(_.seatPosition == passenger.seatPreference)
      .orElse(availableClassSeats.find(_.seatPosition != Middle))
      .orElse(availableClassSeats.headOption)
      .map(s => {
        val available = availableSeats - s
        val assigned = assignedSeats + ((s, passenger))

        (s, Plane(available, assigned))
      })

  }
}


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

sealed trait SeatPosition

case object Aisle extends SeatPosition

case object Middle extends SeatPosition

case object Window extends SeatPosition

sealed trait Seat {
  val row: Int
  val seat: Char
  val seatingClass: SeatingClass
  val seatPosition: SeatPosition
}

case class FirstClassSeat(row: Int, seat: Char, seatPosition: SeatPosition) extends Seat {
  final val seatingClass = FirstClass
}

case class BusinessClassSeat(row: Int, seat: Char, seatPosition: SeatPosition) extends Seat {
  final val seatingClass = BusinessClass
}

case class EconomyPlusSeat(row: Int, seat: Char, seatPosition: SeatPosition) extends Seat {
  final val seatingClass = EconomyPlus
}

case class EconomySeat(row: Int, seat: Char, seatPosition: SeatPosition) extends Seat {
  final val seatingClass = Economy
}
