package training.scala.air_scala.flights.scheduling

import org.scalatest.{FreeSpec, MustMatchers}
import training.scala.air_scala.TestData

class ItinerarySpec extends FreeSpec with MustMatchers {
  import TestData._

  "returns false when subsequent flights don't share an airport" in {
    Itinerary.subsequentFlightsShareAirport(nonSharedAirportItinerary) mustBe false
  }
}
