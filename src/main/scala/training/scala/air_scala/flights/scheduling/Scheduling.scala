package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import squants.market.{USD, Money}
import squants.space._
import training.scala.air_scala.airport.AirportCode
import training.scala.air_scala.flights.Flight
import LengthConversions.LengthNumeric

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

  def totalMilesEarned(itinerary: Itinerary): Length = {
    val totals: Iterator[Length] = itinerary.flights.sliding(2) map {
      case f1 +: f2 +: _ => f1.miles + f2.miles
      case _ => NauticalMiles(0)
    }
    totals.sum
  }
}

sealed trait Itinerary {
  val flights: Seq[Flight]
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary
