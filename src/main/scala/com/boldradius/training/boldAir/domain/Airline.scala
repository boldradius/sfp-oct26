package com.boldradius.training.boldAir.domain

sealed trait AircraftType
final case object MD11 extends AircraftType
final case object `747` extends AircraftType
final case object Ospray extends AircraftType

case class Aircraft(aircraftType: AircraftType, id: String)
case class Airline(name: String, aircraft: Set[Aircraft])
case class Seat(row: Int, position: String)


/**
 * Perfect core for future exercise regarding Abstract Types...
 * Passenger would have an abstract type Nationality, which would
 * need to be filled in on a concrete instance of Passenger
 * 
 * Also, add "FrequentFlyer" info... maybe instead?
 */
sealed trait Passenger
final case class Canadian(name: String) extends Passenger
final case class American(name: String) extends Passenger


sealed trait LandingSurface
final case object LandingStrip extends LandingSurface
final case object LandingPad extends LandingSurface
