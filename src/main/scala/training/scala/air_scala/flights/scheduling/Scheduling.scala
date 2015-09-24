package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import squants.market.{USD, Money}
import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight

import scala.annotation.tailrec


object Itinerary {
  def totalPrice(itinerary: Itinerary): Money = {
    @tailrec
    def totalPriceF(flights: Seq[Flight], accum: Money): Money = flights match {
      case flight +: oFlights => totalPriceF(oFlights, flight.price + accum)
      case _ => accum
    }
    totalPriceF(itinerary.flights, USD(0))
  }
}

sealed trait Itinerary {
  val flights: Seq[Flight]
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary
