package training.scala.air_scala.exercises.five

import org.scalatest.{FreeSpec, MustMatchers}
import squants.space.NauticalMiles
import training.scala.air_scala.TestData
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.flights.{FlightNumber, Flight}

class CustomExtractorSpec extends FreeSpec with MustMatchers {
  import TestData._
  import squants.market._


  "The `apply` factory method for Flight" - {
    "Will safely construct a Flight instance" - {
      val flight = Flight(
        FlightNumber("UA", 1683),
        Aircraft(B747),
        SFToNewarkSchedule,
        USD(256.15),
        NauticalMiles(2565)
      )

      flight.number mustBe FlightNumber("UA", 1683)
      flight.aircraft mustBe Aircraft(B747)
      flight.schedule mustBe SFToNewarkSchedule
      flight.price mustBe USD(256.15)
      flight.miles mustBe NauticalMiles(2565)
    }
  }

  "The `unapply` extractor method for Flight" - {
    "Will safely extract the contents of a Flight class" - {
      val f = EWRToLHRFlight
      val extracted = f match {
        case Flight(f.number, f.aircraft, f.schedule, f.price, f.miles) => true
        case _ => false
      }

      extracted mustBe true
    }
  }


}
