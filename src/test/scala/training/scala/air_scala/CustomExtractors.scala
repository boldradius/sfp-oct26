package training.scala.air_scala

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class CustomExtractors extends FreeSpec with MustMatchers {
  import TestHelpers._
  import squants.market._
  import flights.Flight

  "extract fields from Flight class" - {
    "yields expected values" - {
      val flight = arbitraryFlight("N123", USD(300))
      val flightFields = flight match {
        case Flight(aircraft, schedule, price) => Some(aircraft, schedule, price)
        case _ => None
      }
      assert(flightFields === Some(flight.aircraft, flight.schedule, flight.price))
    }
  }
}