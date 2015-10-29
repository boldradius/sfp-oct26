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

case class AssignedSeat(seat: Seat, passenger: Option[Passenger])

case class Plane(seats: Map[SeatingClass, List[AssignedSeat]])

case class Aircraft(model: AircraftModel) {

  def checkInPassenger(passenger: Passenger, plane: Plane): (Option[Seat], Plane) = {

    val seatClass = passenger.seatingClass
    val planeSeats = plane.seats
    val seatSeq = planeSeats(seatClass)

    val availableSeatSeq = seatSeq.filter{case AssignedSeat(_, passenger) => passenger == None}

    val assignedSeat =
      availableSeatSeq
        .find(_.seat.seatPosition == passenger.seatPosition)
        .orElse(seatSeq.find(_.seat.seatPosition != Middle))
        .orElse(seatSeq.headOption)

    assignedSeat.map{
      sp => {
        val unchangedSeatSeq = seatSeq.filterNot(_ == assignedSeat)
        (Some(sp.seat), Plane(planeSeats + (seatClass -> (AssignedSeat(sp.seat, Some(passenger)) +: unchangedSeatSeq))))
      }
    }.getOrElse((None, plane))
  }

  def upgradePassenger(passenger: Passenger, plane: Plane): (Option[Seat], Plane) = {

    passenger.frequentFlyer.flatMap(frequentFlyer =>

      (passenger.seatingClass.priority + 1 to frequentFlyer.priority)
        .toStream
        .map {
          priority =>

            val upgradedPassenger =
              new Passenger(passenger.familyName, passenger.givenName,
                passenger.middleName, passenger.seatPosition, new SeatingClass {
                  override val priority: Int = priority
                }, passenger.frequentFlyer)

            checkInPassenger(upgradedPassenger, plane)

        }.find { case (a, _) => a.isDefined }.flatten

    ).getOrElse((None, plane))

  }
}

sealed trait FrequentFlyer {
  val priority: Int
}

case object Odersky extends FrequentFlyer {
  val priority = FirstClass.priority
}

case object Klang extends FrequentFlyer {
  val priority = BusinessClass.priority
}

case object Kelland extends FrequentFlyer {
  val priority = EconomyPlus.priority
}

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

sealed trait SeatPosition {
  val name: String
}

case object Aisle extends SeatPosition {
  val name = "Aisle"
}

case object Middle extends SeatPosition {
  val name = "Middle"
}

case object Window extends SeatPosition {
  val name = "Window"
}

sealed trait Seat {
  val row: Int
  val seat: Char
  val seatingClass: SeatingClass
  val seatPosition: SeatPosition
}

class Passenger(val familyName: String,
                val givenName: String,
                val middleName: Option[String],
                val seatPosition: SeatPosition,
                val seatingClass: SeatingClass,
                val frequentFlyer: Option[FrequentFlyer]) {

  require(seatPosition != Middle) // won't be able to recover from exception

}

/*case class FirstClassSeat(row: Int, seat: Char) extends Seat {
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
}*/
