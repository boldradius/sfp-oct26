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

  def totalMilesEarned(itinerary: Itinerary): Double = {
    val totals = itinerary.flights.sliding(2) map {
      case f1 +: f2 +: _ =>
        println(s"F1: $f1 F2: $f2")
        f1.miles.toNauticalMiles + f2.miles.toNauticalMiles
      case _ => 0.0
    }
    val totalsV = totals.toVector
    println(totalsV)
    println(totalsV.sum)
    totalsV.sum
  }
}

sealed trait Itinerary {
  val flights: Seq[Flight]
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary
