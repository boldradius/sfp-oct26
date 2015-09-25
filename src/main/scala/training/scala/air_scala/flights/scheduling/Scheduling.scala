package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import squants.market.{USD, Money}
import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight

import scala.annotation.tailrec


object Itinerary { }

sealed trait Itinerary {
  val flights: Seq[Flight]
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary
