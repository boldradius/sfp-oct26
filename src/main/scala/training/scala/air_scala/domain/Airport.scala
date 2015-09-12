package com.boldradius.training.boldAir.domain

import scala.language.implicitConversions

case class Airport(code: AirportCode,
                   name: String,
                   gates: Set[Gate],
                   landingSurfaces: Set[LandingSurface])

object AirportCode {
  implicit def toCode(str: String) = AirportCode(str)
}
case class AirportCode(str: String) {
  require(str.matches("[A-Z][A-Z][A-Z]"), "Must be 3 uppercase letters")
}

case class Gate(id: String, aircraftTypes: Set[AircraftType])
