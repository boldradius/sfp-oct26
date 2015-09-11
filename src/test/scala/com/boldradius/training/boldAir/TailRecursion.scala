package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class TailRecusion extends FreeSpec with MustMatchers {
  import TestHelpers._
  import squants.market._
  import domain._
  import services.FlightPriceTailrec._

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
