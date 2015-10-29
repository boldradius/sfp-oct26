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

case class Plane(seats: Set[Seat]) {
  def reserve(passenger: Passenger): Option[(Seat, Plane)] = {

    val availableClassSeats = seats.filter(_.seatingClass == passenger.seatingClass)

    availableClassSeats
      .find(_.seatPosition == passenger.seatPreference)
      .orElse(availableClassSeats.find(_.seatPosition != Middle))
      .orElse(availableClassSeats.headOption)
      .map(s => (s, Plane(seats - s)))

  }

  def upgradeSeat(passenger: Passenger, pseat:Seat): Option[(Seat, Plane)] = {
    val availableFirstClassSeats = seats.filter(_.seatingClass == FirstClass)
    val availableBusinessClassSeats = seats.filter(_.seatingClass == BusinessClass)
    val availableEconomyPlusClassSeats = seats.filter(_.seatingClass == EconomyPlus)
    val availableEconomyClassSeats = seats.filter(_.seatingClass == Economy)
    val optS  = passenger.frequentFlyer match {
      case Odersky => availableFirstClassSeats.headOption.
                      orElse(availableBusinessClassSeats.headOption).
                      orElse(availableEconomyPlusClassSeats.headOption).
                      orElse(availableEconomyClassSeats.headOption)
      case Klang => availableBusinessClassSeats.headOption.
                    orElse(availableEconomyPlusClassSeats.headOption).
                    orElse(availableEconomyClassSeats.headOption)
      case Kelland => if(passenger.seatingClass==Economy) availableEconomyPlusClassSeats.headOption else None
      case _ => None
    }
    optS.map((s:Seat)=>(s, Plane(seats - s + pseat)))
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

sealed trait FrequentFlyer {
}

case object Odersky extends FrequentFlyer {

}
case object Klang extends FrequentFlyer {

}
case object Kelland extends FrequentFlyer {

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
