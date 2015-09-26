package training.scala.air_scala.exercises.five

import org.scalatest.exceptions.TestFailedException
import org.scalatest.{FreeSpec, MustMatchers}
import squants.space.NauticalMiles
import training.scala.air_scala.TestData
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.flights.{FlightNumber, Flight}

class CustomExtractorSpec extends FreeSpec with MustMatchers {
  import org.scalatest.Assertions._
  import TestData._
  import squants.market._


  "The `unapply` extractor method for FlightNumber" - {
    "Will safely extract the contents of a String into a FlightNumber case class" in {

      "UA940" match {
        case FlightNumber(code, number) =>
          assert(code == "UA")
          assert(number == 940)
        case _ =>
          fail("Invalid extraction of FlightNumber.")
      }

      "BA1023" match {
        case FlightNumber(code, number) =>
          assert(code == "BA")
          assert(number == 1023)
        case _ =>
          fail("Invalid extraction of FlightNumber.")
      }

      "AA8999" match {
        case FlightNumber(code, number) =>
          assert(code == "AA")
          assert(number == 8999)
        case _ =>
          fail("Invalid extraction of FlightNumber.")
      }

    }
    "Will safely fail when input is bad" in {
      "XYZNO123" match {
        case FlightNumber(code, number) =>
          fail("Invalid FlightNumber extraction.")
        case _ =>
          assert(true)
      }
    }
  }


}
