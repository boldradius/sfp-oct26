package training.scala.air_scala

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers
import training.scala.air_scala.flights.scheduling.ProposedItinerary

class FoldingSpec extends FreeSpec with MustMatchers {
  import TestData._
  import squants.market._
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
        val itinerary = ProposedItinerary(Seq.empty)
        totalPriceFold(itinerary) mustBe USD(0)
      }
    }
    "non-empty itinerary" - {
      "cost is sum of legs" in {

        val flights = Seq(
          sfoToEwrSegment, ewrToSfoSegment
        )
        val itinerary = ProposedItinerary(flights)

        totalPriceFold(itinerary) mustBe flights(0).price + flights(1).price
      }
    }
  }
}
