package training.scala.air_scala.airport

import training.scala.air_scala.aircraft.AircraftModel

case class Airport(code: AirportCode,
                   name: String,
                   gates: Set[Gate],
                   landingSurfaces: Set[LandingSurface])



// todo - should we have terminal id & gate id?
// todo - this should be aircraft CLASS
case class Gate(id: String, aircraftTypes: Set[AircraftModel])
