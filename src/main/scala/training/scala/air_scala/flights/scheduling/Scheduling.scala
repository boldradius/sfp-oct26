package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight

case class FlightLeg(code: AirportCode, departureTime: DateTime)

case class Schedule(origin: FlightLeg, destination: FlightLeg)

case class ProposedItinerary(flights: Seq[Flight])
