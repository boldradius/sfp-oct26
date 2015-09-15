package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight

case class Schedule(origin: (AirportCode, DateTime), destination: (AirportCode, DateTime))

case class ProposedItinerary(flights: Seq[Flight])
