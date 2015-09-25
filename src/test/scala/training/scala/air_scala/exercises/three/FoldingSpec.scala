package training.scala.air_scala.exercises.three

import org.scalatest.{FreeSpec, MustMatchers}
import training.scala.air_scala.TestData
import training.scala.air_scala.flights.scheduling.{ProposedItinerary, Itinerary}

class FoldingSpec extends FreeSpec with MustMatchers {
  import TestData._
  import squants.market._
  import com.github.nscala_time.time.Implicits._


  "totalTravelTime calculates the total time, with layovers, during a flight Itinerary" - {
    "empty itinerary" - {
      "is 0 duration" in {
        val itinerary = ProposedItinerary(Seq.empty)
        //Itinerary.totalTravelTime(itinerary) mustBe
      }
    }
    "single item itinerary" - {
      "is 0 duration" in {
        val itinerary = ProposedItinerary(Seq(sfoToEwrSegment))
        //Itinerary.totalTravelTime(itinerary) mustBe
      }
    }
    "non-empty itinerary" - {
      "a valid itinerary should give us a proper duration of flights, with layover time" - {

        val flights = Seq(
          sfoToEwrSegment,
          ewrToLhrSegment,
          lhrToEWRSegment,
          ewrToSFOSegment
        )

        val sfoToLHRItinerary = ProposedItinerary(Seq(sfoToEwrSegment, ewrToLhrSegment))

        val totalSfoToLhrTime = Itinerary.totalTravelTime(sfoToLHRItinerary)

        println(
         s"Hours: ${totalSfoToLhrTime.getHours} " +
           s"Minutes: ${totalSfoToLhrTime.getMinutes} " +
           s"Seconds: ${totalSfoToLhrTime.getSeconds}"
        )

      }
      "an invalid itinerary should give us funky answers" - {

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
