package training.scala.air_scala.exercises.two

import org.scalatest.{FreeSpec, MustMatchers}
import squants.space.NauticalMiles
import training.scala.air_scala.TestData
import training.scala.air_scala.flights.scheduling.{Itinerary, ProposedItinerary}

class SlidingPFSpec extends FreeSpec with MustMatchers {
  import TestData._
  import squants.market._

  "totalMilesEarned computes the total flight miles, by summing the mileage of the legs" - {
    "empty itinerary" - {
      "mileage is zero" in {
        val itinerary = ProposedItinerary(Seq.empty)
        Itinerary.totalMilesEarned(itinerary) mustBe NauticalMiles(0)
      }
    }
    "non-empty itinerary" - {
      "total mileage is sum of legs" in {

        val flights = Seq(
          sfoToEwrSegment,
          ewrToSfoSegment
        )

        val itinerary = ProposedItinerary(flights)

        Itinerary.totalMilesEarned(itinerary) mustBe (
          sfoToEwrSegment.miles + ewrToSfoSegment.miles
        )
      }
    }
  }

}
