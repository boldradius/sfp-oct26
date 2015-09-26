package training.scala.air_scala.exercises.six

import org.scalatest.{ FreeSpec, MustMatchers }
import squants.space.NauticalMiles
import training.scala.air_scala.TestData
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.flights.scheduling.Itinerary
import training.scala.air_scala.flights.{ Flight, FlightNumber }

class CustomSequenceExtractorSeq extends FreeSpec with MustMatchers {
  import TestData._
  import squants.market._

  "The `unapplySeq` factory method for Itinerary" - {
    "Will safely extract the `Flight`s of an `Itinerary` as a `Seq[Flight]`" - {
      val extracted = SFToLondonRoundTripItinerary match {
        case Itinerary(
          Flight(FlightNumber("UA", 1683), _, _, _, _),
          Flight(_, _, _, _, NauticalMiles(5199)),
          _,
          Flight(_, _, _, USD(382.26), _)
        )      => true
        case _ => false
      }

      extracted mustBe true
    }
  }

}
