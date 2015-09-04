package com.boldradius.domain

import org.scalatest.FreeSpec

class DomainTests extends FreeSpec {
  "the example domain" - {
    "an airport" - {
      "has a code and name" in {
        val code = AirportCode("YXE")
        val name = "Saskatoon"
        val airport = Airport(code, name)
        assert(airport.code === code)
        assert(airport.name === name)
      }
      "has a set of gates" in {
        //TODO
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
        val dplane = Aircraft()
        val delta = Airline("Delta", Set(dplane))
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
        val dplane = Aircraft()
        //TODO
      }
    }
    "a passenger" - {
      //TODO
    }
    "a seat" - {
      //TODO
    }
    "a schedule" - {
      //TODO
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
