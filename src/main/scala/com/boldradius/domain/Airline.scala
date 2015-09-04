package com.boldradius.domain


sealed trait AircraftType
final case object MD11 extends AircraftType

case class Aircraft()
case class Airline(name: String, aircraft: Set[Aircraft])
