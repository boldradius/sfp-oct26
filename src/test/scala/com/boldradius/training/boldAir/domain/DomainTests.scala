package com.boldradius.training.boldAir.domain

import org.scalatest.FreeSpec
import org.joda.time.DateTime

class DomainTests extends FreeSpec {
  "the example domain" - {
    "an airport" - {
      "has a code and name, and a set of gates" in {
        val code = AirportCode("YXE")
        val name = "Saskatoon"
        val gates = Set(Gate("B12", Set(MD11)))
        val airport = Airport(code, name, gates)
        assert(airport.code === code)
        assert(airport.name === name)
      }
    }
    "a gate" - {
      "has an identifier and a set of aircraft types" in {
        val id = "B14"
        val types: Set[AircraftType] = Set(MD11)
        val g = Gate(id, types)
        assert(g.id === id)
        assert(g.aircraftTypes === types)
      }
    }
    "an airline" - {
      "has a name and a set of aircraft" in {
        val dplanes = Set(Aircraft(MD11, "N1234"))
        val delta = Airline("Delta", dplanes)
        assert(delta.aircraft === dplanes)
      }
    }
    "a flight" - {
      "has a schedule plus an aircraft" in {
        val aircraft = Aircraft(MD11, "N1234")
        val sched = arbitrarySchedule
        val flight = Flight(aircraft, sched)
        assert(flight.aircraft === aircraft)
        assert(flight.schedule === sched)
      }
    }
    "an itinerary" - {
      "a tentative itinerary" - {
        "has a sequence of flights" in {
          val flights = Seq(arbitraryFlight("N123"), arbitraryFlight("N234"))
          val itin = TentativeItinerary(flights)
          assert(itin.flights === flights)
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
        assert(dplane.aircraftType === typ)
        assert(dplane.id === id)
      }
    }
    "a passenger" - {
      "has a name" in {
        val name = "Joe Smith"
        val p = Passenger(name)
        assert(p.name === name)
      }
    }
    "a seat" - {
      "has a row and position" in {
        val row = 41
        val position = "F"
        val seat = Seat(row, position)
        assert(seat.row === row)
        assert(seat.position === position)
      }
    }
    "a schedule" - {
      "has an origin and a destination, a start date/time and end date/time" in {
        val origin = AirportCode("YXE")
        val dest = AirportCode("MSP")
        val now = new DateTime()
        val later = new DateTime()
        val sched = Schedule((origin, now), (dest, later))
        assert(sched.origin === (origin, now))
        assert(sched.destination === (dest, later))
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
        assert(mkCode(yxe) === AirportCode("YXE"))
      }
    }
  }

  def arbitrarySchedule = Schedule((AirportCode("YXE"), new DateTime()), (AirportCode("YYZ"), new DateTime()))
  def arbitraryFlight(id: String) = Flight(Aircraft(MD11, id), arbitrarySchedule)
}
