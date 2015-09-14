package training.scala.air_scala.domain

case class Airport(code: AirportCode,
                   name: String,
                   gates: Set[Gate],
                   landingSurfaces: Set[LandingSurface])

object AirportCode {
  implicit def toCode(str: String): AirportCode = AirportCode(str)
}
case class AirportCode(str: String) {
  require(str.matches("[A-Z]{3}"), "Airport Code must consist of 3 uppercase letters.")
}

// todo - should we have terminal id & gate id?
case class Gate(id: String, aircraftTypes: Set[AircraftModel])
