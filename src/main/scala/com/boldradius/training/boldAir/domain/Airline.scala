package com.boldradius.training.boldAir.domain

sealed trait AircraftType
final case object MD11 extends AircraftType
final case object `747` extends AircraftType
final case object Ospray extends AircraftType

case class Aircraft(aircraftType: AircraftType, id: String)
case class Airline(name: String, aircraft: Set[Aircraft])
case class Seat(row: Int, position: String)


sealed trait Passenger
final case class Male(name: String) extends Passenger
final case class Female(name: String) extends Passenger


sealed trait LandingSurface
final case object LandingStrip extends LandingSurface
final case object LandingPad extends LandingSurface
