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
