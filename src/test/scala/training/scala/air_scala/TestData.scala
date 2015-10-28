package training.scala.air_scala

import squants.space.NauticalMiles
import training.scala.air_scala.aircraft._
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.airport.{Gate, AirportCode}
import training.scala.air_scala.flights.scheduling.ProposedItinerary
import training.scala.air_scala.flights.{FlightNumber, FlightLeg, Schedule, Flight}
import com.github.nscala_time.time.Imports._
import squants.market._
import squants.space.Length._
import MoneyConversions._


import scala.collection.immutable.HashMap

object TestData {
  def rows(sClass: SeatingClass, fromRow: Int, toRow: Int, row: List[(Char, SeatPosition)]) : (SeatingClass, Vector[Seat]) =
    sClass -> (fromRow to toRow).toVector.flatMap(r => row.map{case (seat, pos) => Seat(r, seat, EconomyPlus, pos)})

  case object CRJ200 extends AircraftModel with TurboProp {
    val row = List('A' -> Window, 'B' -> Aisle, 'C' -> Window)
    val seats = HashMap[SeatingClass, Vector[Seat]](
      FirstClass -> Vector.empty[Seat],
      BusinessClass -> Vector.empty[Seat],
      rows(EconomyPlus, 1, 4, row),
      rows(EconomyPlus, 5, 13, row)
    )
  }

  case object MD11 extends AircraftModel with WideBodyJet {
    val businessRow = List('A' -> Window, 'B' -> Aisle, 'C' -> Aisle, 'D' -> Window)
    val row = List('A' -> Window, 'B' -> Middle, 'C' -> Aisle, 'D' -> Aisle, 'E' -> Middle, 'F' -> Aisle, 'G' -> Aisle, 'H' -> Middle, 'J' -> Window)
    val seats = HashMap[SeatingClass, Vector[Seat]](
      FirstClass -> Vector.empty[Seat],
      rows(BusinessClass, 1, 5, businessRow),
      rows(EconomyPlus, 6, 22, row),
      rows(Economy, 23, 40, row))
  }

  case object B747 extends AircraftModel with WideBodyJet {
    val firstRow = List('A' -> Window, 'D' -> Window)
    val businessRow = List('A' -> Window, 'B' -> Aisle, 'C' -> Aisle, 'D' -> Window)
    val row = List('A' -> Window, 'B' -> Middle, 'C' -> Aisle, 'D' -> Aisle, 'E' -> Middle, 'F' -> Aisle, 'G' -> Aisle, 'H' -> Middle, 'J' -> Window)
    val seats = HashMap[SeatingClass, Vector[Seat]](
      rows(FirstClass, 1, 5, firstRow),
      rows(BusinessClass, 6, 10, businessRow),
      rows(EconomyPlus, 11, 22, row),
      rows(Economy, 23, 40, row))
  }

  // TODO - some of our tests with time comparisons may race based on daylight savings...

  val SFOToEWRDeparture = new DateTime(2018, 6, 12, 10, 49, DateTimeZone.forID("US/Pacific"))

  val EWRFromSFOArrival = new DateTime(2018, 6, 12, 19, 29, DateTimeZone.forID("US/Eastern"))

  val EWRToLHRDeparture = new DateTime(2018, 6, 12, 21, 49, DateTimeZone.forID("US/Eastern"))

  val LHRFromEWRArrival = new DateTime(2018, 6, 13, 10, 19, DateTimeZone.forID("Europe/London"))

  val LHRToEWRDeparture = new DateTime(2018, 6, 21, 8, 40, DateTimeZone.forID("Europe/London"))

  val EWRFromLHRArrival = new DateTime(2018, 6, 21, 11, 45, DateTimeZone.forID("US/Eastern"))

  val EWRToSFODeparture = new DateTime(2018, 6, 21, 14, 15, DateTimeZone.forID("US/Eastern"))

  val SFOFromEWRArrival = new DateTime(2018, 6, 21, 17, 27, DateTimeZone.forID("US/Pacific"))

  val SFO = AirportCode("SFO")

  val EWR = AirportCode("EWR")

  val LHR = AirportCode("LHR")

  def SFToNewarkSchedule = Schedule(
    FlightLeg(SFO, SFOToEWRDeparture),
    FlightLeg(EWR, EWRFromSFOArrival)
  )

  def NewarkToLondonSchedule = Schedule(
    FlightLeg(EWR, EWRToLHRDeparture),
    FlightLeg(LHR, LHRFromEWRArrival)
  )

  def LondonToNewarkSchedule = Schedule(
    FlightLeg(LHR, LHRToEWRDeparture),
    FlightLeg(EWR, EWRFromLHRArrival)
  )

  def NewarkToSFSchedule = Schedule(
    FlightLeg(EWR, EWRToSFODeparture),
    FlightLeg(SFO, SFOFromEWRArrival)
  )

  implicit val moneyContext = defaultMoneyContext
  implicit val moneyNum = new MoneyNumeric()

  def SFOToEWRFlight =
    new Flight(
      FlightNumber("UA", 1683),
      Aircraft(B747),
      SFToNewarkSchedule,
      USD(256.15),
      NauticalMiles(2565)
    )

  val SFToNewarkItinerary = ProposedItinerary(
    Seq(
      SFOToEWRFlight
    )
  )
  
  def EWRToLHRFlight =
    new Flight(
      FlightNumber("UA", 940),
      Aircraft(B747),
      NewarkToLondonSchedule,
      USD(1419),
      NauticalMiles(5199)
    )

  val NewarkToLondonItinerary = ProposedItinerary(
    Seq(
      EWRToLHRFlight
    )
  )

  val SFToLondonItinerary = ProposedItinerary(
    Seq(
      SFOToEWRFlight,
      EWRToLHRFlight
    )
  )

  def LHRToEWRFlight =
    new Flight(
      FlightNumber("UA", 923),
      Aircraft(B747),
      LondonToNewarkSchedule,
      USD(1738),
      NauticalMiles(5199)
    )

  val LondonToNewarkItinerary = ProposedItinerary(
    Seq(
      LHRToEWRFlight
    )
  )


  def EWRToSFOFlight =
    new Flight(
      FlightNumber("UA", 1978),
      Aircraft(B747),
      NewarkToSFSchedule,
      USD(382.26),
      NauticalMiles(2565)
    )

  val NewarkToSFItinerary = ProposedItinerary(
    Seq(
      EWRToSFOFlight
    )
  )

  val LondonToSFItinerary = ProposedItinerary(
    Seq(
      LHRToEWRFlight,
      EWRToSFOFlight
    )
  )

  // todo ++ for Itineraries
  val SFToLondonRoundTripItinerary = ProposedItinerary(
    Seq(
      SFOToEWRFlight,
      EWRToLHRFlight,
      LHRToEWRFlight,
      EWRToSFOFlight
    )
  )

}
