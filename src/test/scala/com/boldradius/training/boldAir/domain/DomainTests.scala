package com.boldradius.training.boldAir.domain

import org.scalatest.FreeSpec

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
        val dplanes = Set(Aircraft(MD11))
        val delta = Airline("Delta", dplanes)
        assert(delta.aircraft === dplanes)
      }
    }
    "an itinerary" - {
      "a tentative itinerary" - {
        //TODO
      }
      "a booked itinerary" - {
      }
      //TODO
    }
    "an aircraft" - {
      "has a type, a set of seats, an identifier and an optional schedule" in {
        val typ = MD11
        val dplane = Aircraft(typ)
        assert(dplane.aircraftType === typ)
      }
    }
    "a passenger" - {
      //TODO
    }
    "a seat" - {
      //TODO
    }
    "a schedule" - {
      import org.joda.time.DateTime

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
}
