package training.scala.air_scala.domain

import org.scalatest.FreeSpec
import org.scalatest.MustMatchers
import com.github.nscala_time.time.Imports._
import squants.market._
import training.scala.air_scala.TestHelpers._

class DomainTests extends FreeSpec with MustMatchers {
  "the example domain" - {
    "an airport" - {
      "has a code and name, and a set of gates" in {
        val code = AirportCode("YXE")
        val name = "Saskatoon"
        val gates = Set(Gate("B12", Set(MD11)))
        // Possibly an opportunity to introduce variance annotations
        // to get better type inference:
        val landingSurfaces: Set[LandingSurface] = Set(LandingStrip, LandingPad)
        val airport = Airport(code, name, gates, landingSurfaces)
        airport.code mustBe code
        airport.name mustBe name
        airport.gates mustBe gates
        airport.landingSurfaces mustBe landingSurfaces
      }
    }
    "a gate" - {
      "has an identifier and a set of aircraft types" in {
        val id = "B14"
        val types: Set[AircraftModel] = Set(MD11)
        val g = Gate(id, types)
        g.id mustBe id
        g.aircraftTypes mustBe types
      }
    }
    "an airline" - {
      "has a name and a set of aircraft" in {
        val dplanes = Set(Aircraft(MD11, "N1234"))
        val delta = Airline("Delta", dplanes)
        delta.aircraft mustBe dplanes
      }
    }
    "a flight" - {
      "has a schedule, an aircraft, and a price" in {
        val aircraft = Aircraft(MD11, "N1234")
        val sched = arbitrarySchedule
        val price = USD(300)
        val flight = Flight(aircraft, sched, price)
        flight.aircraft mustBe aircraft
        flight.schedule mustBe sched
        flight.price mustBe price
      }
    }
    "an itinerary" - {
      "a tentative itinerary" - {
        "has a sequence of flights" in {
          val flights = Seq(arbitraryFlight("N123", USD(100)), arbitraryFlight("N234", USD(100)))
          val itin = TentativeItinerary(flights)
          itin.flights mustBe flights
        }
      }
      "a booked itinerary" - {
      }
    }
    "an aircraft" - {
      "has a type, a set of seats and an identifier" in {
        val typ = MD11
        val id = "N1234"
        val dplane = Aircraft(typ, id)
        dplane.aircraftType mustBe typ
        dplane.id mustBe id
      }
    }
    "a passenger" - {
      "has a name" in {
        val name = "Joe Smith"
        val passenger = Canadian(name)
        passenger.name mustBe name
      }
    }
    "a seat" - {
      "has a row and position" in {
        val row = 41
        val position = "F"
        val seat = Seat(row, position)
        seat.row mustBe row
        seat.position mustBe position
      }
    }
    "a schedule" - {
      "has an origin and a destination, a start date/time and end date/time" in {
        val origin = AirportCode("YXE")
        val dest = AirportCode("MSP")
        val now = new DateTime()
        val later = new DateTime()
        val sched = Schedule((origin, now), (dest, later))
        sched.origin mustBe (origin, now)
        sched.destination mustBe (dest, later)
      }
    }
    "an airport code" - {
      "must be 3 uppercase letters" in {
        intercept[IllegalArgumentException] {
          AirportCode("foo")
        }
      }
      "can be created from a string" in {
        val yxe = "YXE"
        def mkCode(code: AirportCode) = code
        mkCode(yxe) mustBe AirportCode("YXE")
      }
    }
  }

}
