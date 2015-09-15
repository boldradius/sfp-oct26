package training.scala.air_scala

import training.scala.air_scala._
import com.github.nscala_time.time.Imports._
import squants.market._
import training.scala.air_scala.flights.Flight
import training.scala.air_scala.flights.scheduling.ProposedItinerary

import scala.annotation.tailrec

object FlightPriceTailrec {
  def totalPrice(itinerary: ProposedItinerary): Money = {
    @tailrec
    def totalPriceF(flights: Seq[Flight], accum: Money): Money = flights match {
      case flight +: oFlights => totalPriceF(oFlights, flight.price + accum)
      case _ => accum
    }
    totalPriceF(itinerary.flights, USD(0))
  }
}

object FlightPriceFold {
  def foldLeft[A,B](seq: Seq[A])(initValue: B)(f: (B,A) => B) = {
    @tailrec
    def foldLeftF(accum: B, seq: Seq[A]): B = seq match {
      case elem +: tail => foldLeftF(f(accum, elem), tail)
      case _ => accum
    }
    foldLeftF(initValue, seq)
  }

  def totalPriceFold(itinerary: ProposedItinerary): Money =
    foldLeft(itinerary.flights)(USD(0))(_ + _.price)
}
