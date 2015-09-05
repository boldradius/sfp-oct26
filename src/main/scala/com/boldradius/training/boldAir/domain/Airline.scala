package com.boldradius.training.boldAir.domain

sealed trait AircraftType
final case object MD11 extends AircraftType

case class Aircraft(aircraftType: AircraftType)
case class Airline(name: String, aircraft: Set[Aircraft])
