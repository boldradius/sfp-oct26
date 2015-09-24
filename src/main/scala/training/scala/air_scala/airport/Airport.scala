package training.scala.air_scala.airport

import training.scala.air_scala.aircraft.{AircraftClass, AircraftModel}

case class Airport(code: AirportCode,
                   name: String,
                   gates: Set[Gate],
                   landingSurfaces: Set[LandingSurface])

// Let's just make all terminals by Character Code.
case class Gate(terminal: Char,
                id: String,
                aircraftClasses: Set[AircraftClass])
