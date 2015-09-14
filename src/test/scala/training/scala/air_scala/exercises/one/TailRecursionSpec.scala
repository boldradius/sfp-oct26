package training.scala.air_scala.exercises.one

import org.scalatest.{FreeSpec, MustMatchers}
import training.scala.air_scala.{FlightPriceTailrec, TestHelpers, domain}

class TailRecursionSpec extends FreeSpec with MustMatchers {
  import FlightPriceTailrec._
  import TestHelpers._
  import domain._
  import squants.market._

  "totalPrice computes the total flight cost, by summing the costs of the legs" - {
    "empty itinerary" - {
      "cost is zero" in {
        val itinerary = TentativeItinerary(Seq.empty)
        totalPrice(itinerary) mustBe USD(0)
      }
    }
    "non-empty itinerary" - {
      "cost is sum of legs" in {
        val leg1Price = USD(100)
        val leg2Price = USD(200)

        val flights = Seq(
          arbitraryFlight("N123", leg1Price), arbitraryFlight("N234", leg2Price))
        val itinerary = TentativeItinerary(flights)

        totalPrice(itinerary) mustBe leg1Price + leg2Price
      }
    }
  }
}
