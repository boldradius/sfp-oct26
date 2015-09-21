package training.scala.air_scala.flights

import squants.market.Money
import training.scala.air_scala.aircraft.Aircraft
import training.scala.air_scala.flights.scheduling._

class Flight(val aircraft: Aircraft, val schedule: Schedule, val price: Money)

object Flight {
  def apply(aircraft: Aircraft, schedule: Schedule, price: Money): Flight =  new Flight(aircraft, schedule, price)
  def unapply(flight: Flight): Option[(Aircraft, Schedule, Money)] = Some(flight.aircraft, flight.schedule, flight.price)
}