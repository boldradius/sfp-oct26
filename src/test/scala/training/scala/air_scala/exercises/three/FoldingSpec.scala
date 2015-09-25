package training.scala.air_scala.exercises.three

import org.scalatest.{FreeSpec, MustMatchers}
import training.scala.air_scala.TestData
import training.scala.air_scala.flights.scheduling.{ProposedItinerary, Itinerary}

class FoldingSpec extends FreeSpec with MustMatchers {
  import TestData._
  import com.github.nscala_time.time.Imports._


  "totalFlightTime calculates the total time, with layovers, during a flight Itinerary" - {
    "empty itinerary" - {
      "is 0 duration" in {
        val itinerary = ProposedItinerary(Seq.empty)
        Itinerary.totalFlightTime(itinerary) mustBe new Period()
      }
    }
    "single item itinerary" - {
      "is the proper duration" in {
        // todo - abstract me out into a non-hardcoded value for total flight times here
        Itinerary.totalFlightTime(SFToNewarkItinerary).normalizedStandard() mustBe new Period(5, 40, 0, 0)
        Itinerary.totalFlightTime(NewarkToLondonItinerary).normalizedStandard() mustBe new Period(7, 30, 0, 0)
        Itinerary.totalFlightTime(SFToLondonItinerary).normalizedStandard() mustBe new Period(13, 10, 0, 0)
        Itinerary.totalFlightTime(LondonToNewarkItinerary).normalizedStandard() mustBe new Period(8, 5, 0, 0)
        Itinerary.totalFlightTime(NewarkToSFItinerary).normalizedStandard() mustBe new Period(6, 12, 0, 0)
        Itinerary.totalFlightTime(LondonToSFItinerary).normalizedStandard() mustBe new Period(14, 17, 0, 0)
        Itinerary.totalFlightTime(SFToLondonRoundTripItinerary).normalizedStandard() mustBe {
          new Period(3, 27, 0, 0).withDays(1)
        }
      }
    }

  }
}
