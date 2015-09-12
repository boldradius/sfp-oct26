package com.boldradius.training.boldAir

import com.boldradius.training.boldAir._
import com.boldradius.training.boldAir.domain._
import com.github.nscala_time.time.Imports._
import squants.market._

object FlightPriceTailrec {
  def totalPrice(itinerary: TentativeItinerary): Money = {
    @annotation.tailrec
    def helper(flights: Seq[Flight], accum: Money): Money = flights match {
      case Seq() => accum
      case Seq(f, rest @ _*) => helper(rest, f.price + accum)
    }
    helper(itinerary.flights, USD(0))
  }
}

object FlightPriceFold {
  def foldLeft[A,B](seq: Seq[A])(initValue: B)(fn: (B,A) => B) = {
    @annotation.tailrec
    def helper(accum: B, seq: Seq[A]): B = seq match {
      case Seq() => accum
      case Seq(value, rest @ _*) => helper(fn(accum, value), rest)
    }
    helper(initValue, seq)
  }

  def totalPriceFold(itinerary: TentativeItinerary): Money =
    foldLeft(itinerary.flights)(USD(0))(_ + _.price)
}
