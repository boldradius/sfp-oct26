package training.scala.air_scala.flights

import squants.market.Money
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.airport.Gate
import training.scala.air_scala.flights.scheduling._

/*
 __ Implementation Note __
 This is deliberately not a case class, as we'll be using it
 for other purposes later.

*/
class Flight(val number: FlightNumber,
             val aircraft: Aircraft,
             val schedule: Schedule,
             val departureGate: Gate,
             val arrivalGate: Gate,
             val price: Money)

case class FlightNumber(airlineCode: String, number: Int) {
  require(airlineCode.matches("[A-Z]{1-3}"),
          "Airline Code must consist of 1-3 uppercase letters.")
  require(number > 0 && number < 8999,
          "Flight Number be between 1 & 8999")
}