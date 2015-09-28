package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import squants.market.{Money, USD}
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

  def isScheduleIncreasing(itinerary: Itinerary): Boolean = {
    itinerary.flights.sliding(2).forall {
      case f1 +: f2 +: _ => f1 < f2
      case _ => true
    }
  }

  def subsequentFlightsShareAirport(itinerary: Itinerary): Boolean = {
    itinerary.flights.sliding(2).forall {
      case f1 +: f2 +: Nil =>
        f1.schedule.destination.code == f2.schedule.origin.code
      case _ => true
    }
  }

  def totalFlightTime(itinerary: Itinerary): Period = {
    itinerary.flights.foldLeft(new Period()) { (p, f) =>
      // this is just the duration of the *FLIGHT*... it doesn't account for the layover
      p + f.flightDuration
    }
  }

  def totalLayoverTime(itinerary: Itinerary): Period =
    layoverTimes(itinerary).foldLeft(new Period())(_ + _.toPeriod).normalizedStandard

  def layoverTimes(itinerary: Itinerary): Seq[Duration] = {
    case class Accum(layovers: Seq[Duration], lastTime: Option[DateTime])
    val init = Accum(Seq.empty[Duration], None)
    itinerary.flights.foldLeft(init) { (acc, f) =>
      acc match {
        case a @ Accum(layovers, None) =>
          a.copy(lastTime = Some(f.schedule.destination.time))
        case a @ Accum(layovers, Some(lastArrivalTime)) =>
          Accum(layovers :+ (lastArrivalTime to f.schedule.origin.time).toDuration,
                Some(f.schedule.destination.time))
      }
    }.layovers
  }
}

sealed trait Itinerary {
  val flights: Seq[Flight]
  val connectionsCount: Int
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary {

  val connectionsCount = {
    val size = flights.size
    if (size == 0) 0
    else size - 1
  }

}
