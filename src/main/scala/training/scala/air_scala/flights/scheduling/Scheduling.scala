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

  def isScheduleIncreasing(itinerary: Itinerary): Boolean = {
    itinerary.flights.sliding(2).forall {
      case f1 +: f2 +: _ => f1 < f2
      case _ => true
    }
  }

  def totalFlightTime(itinerary: Itinerary): Period = {
    itinerary.flights.foldLeft(new Period()) { (p, f) =>
      println(s"Current Period: $p Current Flight Period: ${f.flightDuration}")
      // this is just the duration of the *FLIGHT*... it doesn't account for the layover
      p + f.flightDuration
    }
  }
}

sealed trait Itinerary {
  val flights: Seq[Flight]
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary
