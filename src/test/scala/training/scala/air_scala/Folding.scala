package com.boldradius.training.boldAir

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers

class FoldingSpec extends FreeSpec with MustMatchers {
  import TestHelpers._
  import squants.market._
  import domain._
  import FlightPriceFold._

  "foldLeft" - {

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

  "totalPrice computes the total flight cost, by summing the costs of the legs" - {

    "empty itinerary" - {
      "cost is zero" in {
        val itinerary = TentativeItinerary(Seq.empty)
        totalPriceFold(itinerary) mustBe USD(0)
      }
    }
    "non-empty itinerary" - {
      "cost is sum of legs" in {
        val leg1Price = USD(100)
        val leg2Price = USD(200)

        val flights = Seq(
          arbitraryFlight("N123", leg1Price), arbitraryFlight("N234", leg2Price))
        val itinerary = TentativeItinerary(flights)

        totalPriceFold(itinerary) mustBe leg1Price + leg2Price
      }
    }
  }
}
