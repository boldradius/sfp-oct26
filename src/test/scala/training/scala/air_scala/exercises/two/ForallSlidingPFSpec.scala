package training.scala.air_scala.exercises.two

import org.scalatest.{FreeSpec, MustMatchers}
import squants.space.{Length, NauticalMiles}
import training.scala.air_scala.TestData
import training.scala.air_scala.flights.scheduling.{Itinerary, ProposedItinerary}

class ForallSlidingPFSpec extends FreeSpec with MustMatchers {
  import TestData._
  import squants.market._

  "isScheduleIncreasing checks if a flight schedule is strictly increasing in time" - {
    "empty itinerary" - {
      "increasing is true" in {
        val itinerary = ProposedItinerary(Seq.empty)
        Itinerary.isScheduleIncreasing(itinerary) mustBe true
      }
    }
    "single item itinerary" - {
      "increasing is true" in {
        val itinerary = ProposedItinerary(Seq(sfoToEwrSegment))
        Itinerary.isScheduleIncreasing(itinerary) mustBe true
      }
    }
    "non-empty, itinerary" - {
      "a valid itinerary should evaluate as increasing" - {

        val flights = Seq(
          sfoToEwrSegment,
          ewrToLhrSegment,
          lhrToEWRSegment,
          ewrToSFOSegment
        )

        val itinerary = ProposedItinerary(flights)

        Itinerary.isScheduleIncreasing(itinerary) mustBe true
      }
      "an invalid itinerary should *NOT* evaluate as increasing" - {

        val flights = Seq(
          ewrToLhrSegment,
          sfoToEwrSegment,
          ewrToSFOSegment,
          lhrToEWRSegment
        )

        val itinerary = ProposedItinerary(flights)

        Itinerary.isScheduleIncreasing(itinerary) mustBe false
      }
    }
  }

}
