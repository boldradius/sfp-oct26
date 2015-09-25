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
        val itinerary = SFToNewarkItinerary
        // todo - abstract me out into a non-hardcoded value for total flight times here
        Itinerary.totalFlightTime(itinerary).normalizedStandard() mustBe new Period(5, 40, 0, 0)
      }
    }
    "non-empty itinerary" - {
      "a valid itinerary should give us a proper duration of flights, with layover time" - {
        // todo - abstract me out into a non-hardcoded value for total flight times here
        Itinerary.totalFlightTime(SFToLondonItinerary).normalizedStandard() mustBe new Period(13, 10, 0, 0)
      }
    }
  }
}
