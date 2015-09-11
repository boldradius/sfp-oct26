package com.boldradius.training.boldAir

import org.scalatest.FreeSpec

class FoldingSpec extends FreeSpec {
  import TestHelpers._
  import squants.market._
  import domain._

  "foldLeft" - {
    import Folding._

    val initValue = USD(0)
    "summing a Seq of dollar ammounts" - {
      "empty sequence" in {
        foldLeft(Seq.empty[Money])(initValue)(_ + _) === initValue
      }
    }
    "non-empty sequence" in {
      val value1 = USD(100)
      val value2 = USD(200)
      foldLeft(Seq(value1,value2))(initValue)(_ + _) === initValue + value1 + value2
    }
  }

  "totalPriceFold computes the total flight cost, by summing the costs of the legs" - {
    import services._

    "empty itinerary" - {
      "cost is zero" in {
        val itinerary = TentativeItinerary(Seq.empty)
        assert(Reservations.totalPriceFold(itinerary) === USD(0))
      }
    }
    "non-empty itinerary" - {
      "cost is sum of legs" in {
        val leg1Price = USD(100)
        val leg2Price = USD(200)

        val flights = Seq(
          arbitraryFlight("N123", leg1Price), arbitraryFlight("N234", leg2Price))
        val itinerary = TentativeItinerary(flights)

        assert(Reservations.totalPriceFold(itinerary) === leg1Price + leg2Price)
      }
    }
  }
}
