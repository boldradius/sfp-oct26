package training.scala.air_scala.exercises.one

import org.scalatest.{FreeSpec, MustMatchers}
import training.scala.air_scala.flights.scheduling.{Itinerary, ProposedItinerary}
import training.scala.air_scala.{FlightPriceTailrec, TestHelpers, domain}

class TailRecursionSpec extends FreeSpec with MustMatchers {
  import TestHelpers._
  import domain._
  import squants.market._

  /*
   * Thoughts on valid workthroughs with recursion...
   *
   * Find cheapest itinerary
   * Find itinerary with least connections
   * Find itinerary with best layover options
   * Find itinerary that I'll earn frequent flyer miles on
   * Is itinerary roundtrip?
   */
  "totalPrice computes the total flight cost, by summing the costs of the legs" - {
    "empty itinerary" - {
      "cost is zero" in {
        val itinerary = ProposedItinerary(Seq.empty)
        Itinerary.totalPrice(itinerary) mustBe USD(0)
      }
    }
/*    "non-empty itinerary" - {
      "cost is sum of legs" in {
        val leg1Price = USD(100)
        val leg2Price = USD(200)

        val flights = Seq(
          arbitraryFlight("N123", leg1Price), arbitraryFlight("N234", leg2Price))
        val itinerary = ProposedItinerary(flights)

        totalPrice(itinerary) mustBe leg1Price + leg2Price
      }
    }*/
  }
}
