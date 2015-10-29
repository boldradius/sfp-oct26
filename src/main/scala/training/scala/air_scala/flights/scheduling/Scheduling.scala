package training.scala.air_scala.flights.scheduling

import com.github.nscala_time.time.Imports._
import squants.market.{Money, USD}
import training.scala.air_scala.flights.Flight

import scala.annotation.tailrec


object Itinerary {

  implicit val itineraryOrdered = new Ordering[Itinerary] {
    override def compare(x: Itinerary, y: Itinerary): Int =
      x.flights.map(_.price).reduce(_+_)
        .compare(y.flights.map(_.price).reduce(_+_))
  }

  def unapplySeq(itinerary: Itinerary): Option[Seq[Flight]] = {
    Some(itinerary.flights)
  }

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
      // this is just the duration of the *FLIGHT*... it doesn't account for the layover
      p + f.flightDuration
    }
  }

  def totalLayoverTime(itinerary: Itinerary): Period = {
    case class Accum(p: Period, lastTime: Option[DateTime])
    val init = Accum(new Period(), None)
    itinerary.flights.foldLeft(init) { (acc, f) =>
      acc match {
        case a @ Accum(p, None) =>
          a.copy(lastTime = Some(f.schedule.destination.time))
        case a @ Accum(p, Some(lastArrivalTime)) =>
          Accum((lastArrivalTime to f.schedule.origin.time).toPeriod + p,
                Some(f.schedule.destination.time))
      }
    }.p
  }

}


sealed trait Itinerary {
  val flights: Seq[Flight]
}

case class ProposedItinerary(flights: Seq[Flight]) extends Itinerary
